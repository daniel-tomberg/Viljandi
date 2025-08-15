"use client";

import React, { useState, useEffect } from "react";
import { Order, OrderLine, ordersAPI } from "@/lib/api";
import { ChevronDown, ChevronRight } from "lucide-react";
import FilterInput from "./FilterInput";

interface OrdersTableProps {
  orders: Order[];
}

export default function OrdersTable({ orders }: OrdersTableProps) {
  const [filteredOrders, setFilteredOrders] = useState<Order[]>(orders);
  const [expandedOrder, setExpandedOrder] = useState<string | null>(null);
  const [orderLines, setOrderLines] = useState<OrderLine[]>([]);
  const [loading, setLoading] = useState(false);
  const [filters, setFilters] = useState({
    poNumber: "",
    orderDate: "",
    expectedDelivery: "",
    status: "",
  });

  useEffect(() => {
    setFilteredOrders(orders);
  }, [orders]);

  const handleFilterChange = (field: keyof typeof filters, value: string) => {
    const newFilters = { ...filters, [field]: value };
    setFilters(newFilters);

    const filtered = orders.filter((order) => {
      const poNumberMatch =
        !newFilters.poNumber ||
        order.poNumber
          .toLowerCase()
          .includes(newFilters.poNumber.toLowerCase());

      const orderDateMatch =
        !newFilters.orderDate ||
        order.orderDate
          .toLowerCase()
          .includes(newFilters.orderDate.toLowerCase());

      const expectedDeliveryMatch =
        !newFilters.expectedDelivery ||
        order.expectedDelivery
          .toLowerCase()
          .includes(newFilters.expectedDelivery.toLowerCase());

      const statusMatch =
        !newFilters.status ||
        order.status.toLowerCase().includes(newFilters.status.toLowerCase());

      return (
        poNumberMatch && orderDateMatch && expectedDeliveryMatch && statusMatch
      );
    });

    setFilteredOrders(filtered);
  };

  const handleOrderClick = async (poNumber: string) => {
    if (expandedOrder === poNumber) {
      setExpandedOrder(null);
      setOrderLines([]);
    } else {
      setExpandedOrder(poNumber);
      setLoading(true);
      try {
        const response = await ordersAPI.getOrderLines(poNumber);
        setOrderLines(response.data);
      } catch (error) {
        console.error("Error fetching order lines:", error);
      } finally {
        setLoading(false);
      }
    }
  };

  const getStatusColor = (status: string) => {
    switch (status) {
      case "PROCESSING":
        return "bg-yellow-100 text-yellow-800";
      case "DELIVERED":
        return "bg-green-100 text-green-800";
      case "CANCELLED":
        return "bg-red-100 text-red-800";
      default:
        return "bg-gray-100 text-gray-800";
    }
  };

  return (
    <>
      <div className="mb-4">
        <h2 className="text-lg sm:text-xl font-semibold text-gray-800 mb-1">
          Orders & Delivery Info
        </h2>
        <div className="flex flex-col sm:flex-row sm:justify-between sm:items-center gap-2 sm:gap-0">
          <span className="text-xs sm:text-sm text-gray-600">
            Number of Orders: {filteredOrders.length}
          </span>
          {(filters.poNumber ||
            filters.orderDate ||
            filters.expectedDelivery ||
            filters.status) && (
            <button
              onClick={() => {
                setFilters({
                  poNumber: "",
                  orderDate: "",
                  expectedDelivery: "",
                  status: "",
                });
                setFilteredOrders(orders);
              }}
              className="text-xs sm:text-sm text-blue-600 hover:text-blue-800 underline self-start sm:self-auto"
            >
              Clear Filters
            </button>
          )}
        </div>
      </div>

      <div className="block sm:hidden mb-4 space-y-2">
        <FilterInput
          placeholder="Filter PO Number..."
          value={filters.poNumber}
          onChange={(value) => handleFilterChange("poNumber", value)}
        />
        <FilterInput
          placeholder="Filter Order Date..."
          value={filters.orderDate}
          onChange={(value) => handleFilterChange("orderDate", value)}
        />
        <FilterInput
          placeholder="Filter Expected Delivery..."
          value={filters.expectedDelivery}
          onChange={(value) => handleFilterChange("expectedDelivery", value)}
        />
        <FilterInput
          placeholder="Filter Status..."
          value={filters.status}
          onChange={(value) => handleFilterChange("status", value)}
        />
      </div>

      <div className="bg-gray-100 rounded-lg p-0">
        <div className="hidden sm:block overflow-x-auto">
          <table className="w-full">
            <thead>
              <tr>
                <th className="text-left py-2 px-4 text-gray-700 pl-8">
                  <div className="flex flex-col gap-1">
                    <span>PO Number</span>
                    <FilterInput
                      placeholder="Filter PO Number..."
                      value={filters.poNumber}
                      onChange={(value) =>
                        handleFilterChange("poNumber", value)
                      }
                    />
                  </div>
                </th>
                <th className="text-left py-2 px-4 text-gray-700">
                  <div className="flex flex-col gap-1">
                    <span>Order Date</span>
                    <FilterInput
                      placeholder="Filter Order Date..."
                      value={filters.orderDate}
                      onChange={(value) =>
                        handleFilterChange("orderDate", value)
                      }
                    />
                  </div>
                </th>
                <th className="text-left py-2 px-4 text-gray-700">
                  <div className="flex flex-col gap-1">
                    <span>Expected Delivery</span>
                    <FilterInput
                      placeholder="Filter Expected Delivery..."
                      value={filters.expectedDelivery}
                      onChange={(value) =>
                        handleFilterChange("expectedDelivery", value)
                      }
                    />
                  </div>
                </th>
                <th className="text-left py-2 px-4 text-gray-700">
                  <div className="flex flex-col gap-1">
                    <span>Status</span>
                    <FilterInput
                      placeholder="Filter Status..."
                      value={filters.status}
                      onChange={(value) => handleFilterChange("status", value)}
                    />
                  </div>
                </th>
              </tr>
            </thead>
            <tbody>
              {filteredOrders.map((order) => (
                <React.Fragment key={order.id}>
                  <tr
                    className="border-b border-gray-100 hover:bg-gray-50 cursor-pointer bg-white"
                    onClick={() => handleOrderClick(order.poNumber)}
                  >
                    <td className="py-2 px-2">
                      <div className="flex items-center gap-2">
                        {expandedOrder === order.poNumber ? (
                          <ChevronDown size={16} className="text-gray-500" />
                        ) : (
                          <ChevronRight size={16} className="text-gray-500" />
                        )}
                        <span className="font-medium text-blue-600">
                          {order.poNumber}
                        </span>
                      </div>
                    </td>
                    <td className="py-2 px-2 text-black">{order.orderDate}</td>
                    <td className="py-2 px-2 text-black">
                      {order.expectedDelivery}
                    </td>
                    <td className="py-2 px-2">
                      <span
                        className={`px-2 py-1 rounded-full text-xs font-medium ${getStatusColor(
                          order.status
                        )}`}
                      >
                        {order.status}
                      </span>
                    </td>
                  </tr>
                  {expandedOrder === order.poNumber && (
                    <tr>
                      <td colSpan={4} className="bg-gray-50 p-0">
                        {loading ? (
                          <div className="text-center py-2">
                            Loading order lines...
                          </div>
                        ) : orderLines.length === 0 ? (
                          <div className="text-center py-4 text-gray-500 bg-gray-100">
                            No data loaded.
                          </div>
                        ) : (
                          <div className="overflow-x-auto bg-white">
                            <table className="w-full text-sm">
                              <thead>
                                <tr className="bg-gray-50 border-b border-gray-200">
                                  <th className="text-left py-2 px-2 font-medium text-gray-700 text-xs uppercase tracking-wider">
                                    Line
                                  </th>
                                  <th className="text-left py-2 px-2 font-medium text-gray-700 text-xs uppercase tracking-wider">
                                    OrderNum
                                  </th>
                                  <th className="text-left py-2 px-2 font-medium text-gray-700 text-xs uppercase tracking-wider">
                                    Product Code
                                  </th>
                                  <th className="text-left py-2 px-2 font-medium text-gray-700 text-xs uppercase tracking-wider">
                                    EAN Code
                                  </th>
                                  <th className="text-left py-2 px-2 font-medium text-gray-700 text-xs uppercase tracking-wider">
                                    Product Name
                                  </th>
                                  <th className="text-left py-2 px-2 font-medium text-gray-700 text-xs uppercase tracking-wider">
                                    Qty
                                  </th>
                                  <th className="text-left py-2 px-2 font-medium text-gray-700 text-xs uppercase tracking-wider">
                                    Delivery Date
                                  </th>
                                </tr>
                              </thead>
                              <tbody>
                                {orderLines.map((line, index) => (
                                  <tr
                                    key={line.id}
                                    className="border-b border-gray-100 hover:bg-blue-50 transition-colors duration-150"
                                  >
                                    <td className="py-2 px-2 text-gray-900 font-medium">
                                      <span className="inline-flex items-center justify-center w-6 h-6 bg-blue-100 text-blue-800 text-xs font-semibold rounded-full">
                                        {index + 1}
                                      </span>
                                    </td>
                                    <td className="py-2 px-2 text-gray-900 font-mono text-sm">
                                      {line.orderNum}
                                    </td>
                                    <td className="py-2 px-2 text-gray-900 font-mono text-sm">
                                      {line.productCode}
                                    </td>
                                    <td className="py-2 px-2 text-gray-600 font-mono text-xs">
                                      {line.eanCode}
                                    </td>
                                    <td className="py-2 px-2 text-gray-900 max-w-xs truncate">
                                      {line.productName}
                                    </td>
                                    <td className="py-2 px-2 text-gray-900 font-semibold">
                                      <span className="inline-flex items-center px-2 py-1 bg-green-100 text-green-800 text-xs font-medium rounded-full">
                                        {line.qty.toFixed(2)}
                                      </span>
                                    </td>
                                    <td className="py-2 px-2 text-gray-900">
                                      <span className="inline-flex items-center px-2 py-1 bg-gray-100 text-gray-700 text-xs font-medium rounded-full">
                                        {line.deliveryDate}
                                      </span>
                                    </td>
                                  </tr>
                                ))}
                              </tbody>
                            </table>
                          </div>
                        )}
                      </td>
                    </tr>
                  )}
                </React.Fragment>
              ))}
            </tbody>
          </table>
        </div>

        <div className="block sm:hidden space-y-3 p-3">
          {filteredOrders.map((order) => (
            <div
              key={order.id}
              className="bg-white rounded-lg shadow-sm border border-gray-200 overflow-hidden"
            >
              <div
                className="p-3 cursor-pointer hover:bg-gray-50 transition-colors"
                onClick={() => handleOrderClick(order.poNumber)}
              >
                <div className="flex items-center justify-between mb-2">
                  <div className="flex items-center gap-2">
                    {expandedOrder === order.poNumber ? (
                      <ChevronDown size={16} className="text-gray-500" />
                    ) : (
                      <ChevronRight size={16} className="text-gray-500" />
                    )}
                    <span className="font-medium text-blue-600 text-sm">
                      {order.poNumber}
                    </span>
                  </div>
                  <span
                    className={`px-2 py-1 rounded-full text-xs font-medium ${getStatusColor(
                      order.status
                    )}`}
                  >
                    {order.status}
                  </span>
                </div>
                <div className="space-y-1 text-xs text-gray-600">
                  <div className="flex justify-between">
                    <span>Order Date:</span>
                    <span className="text-gray-900">{order.orderDate}</span>
                  </div>
                  <div className="flex justify-between">
                    <span>Expected Delivery:</span>
                    <span className="text-gray-900">
                      {order.expectedDelivery}
                    </span>
                  </div>
                </div>
              </div>

              {expandedOrder === order.poNumber && (
                <div className="border-t border-gray-100 bg-gray-50">
                  {loading ? (
                    <div className="text-center py-4 text-sm text-gray-500">
                      Loading order lines...
                    </div>
                  ) : orderLines.length === 0 ? (
                    <div className="text-center py-4 text-sm text-gray-500">
                      No data loaded.
                    </div>
                  ) : (
                    <div className="p-3 space-y-3">
                      <h4 className="font-medium text-gray-800 text-sm mb-2">
                        Order Lines
                      </h4>
                      {orderLines.map((line, index) => (
                        <div
                          key={line.id}
                          className="bg-white rounded border border-gray-200 p-3"
                        >
                          <div className="flex items-center justify-between mb-2">
                            <span className="inline-flex items-center justify-center w-6 h-6 bg-blue-100 text-blue-800 text-xs font-semibold rounded-full">
                              {index + 1}
                            </span>
                            <span className="inline-flex items-center px-2 py-1 bg-green-100 text-green-800 text-xs font-medium rounded-full">
                              Qty: {line.qty.toFixed(2)}
                            </span>
                          </div>
                          <div className="space-y-1 text-xs">
                            <div className="flex justify-between">
                              <span className="text-gray-600">Order #:</span>
                              <span className="text-gray-900 font-mono">
                                {line.orderNum}
                              </span>
                            </div>
                            <div className="flex justify-between">
                              <span className="text-gray-600">
                                Product Code:
                              </span>
                              <span className="text-gray-900 font-mono">
                                {line.productCode}
                              </span>
                            </div>
                            <div className="flex justify-between">
                              <span className="text-gray-600">EAN Code:</span>
                              <span className="text-gray-900 font-mono">
                                {line.eanCode}
                              </span>
                            </div>
                            <div className="flex justify-between">
                              <span className="text-gray-600">
                                Delivery Date:
                              </span>
                              <span className="text-gray-900">
                                {line.deliveryDate}
                              </span>
                            </div>
                          </div>
                          <div className="mt-2">
                            <span className="text-gray-600 text-xs">
                              Product:
                            </span>
                            <p className="text-gray-900 text-xs mt-1">
                              {line.productName}
                            </p>
                          </div>
                        </div>
                      ))}
                    </div>
                  )}
                </div>
              )}
            </div>
          ))}
        </div>
      </div>
    </>
  );
}
