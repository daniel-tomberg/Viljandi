"use client";

import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";
import Header from "@/components/Header";
import UpcomingDeliveries from "@/components/UpcomingDeliveries";
import OrdersTable from "@/components/OrdersTable";
import { deliveriesAPI, ordersAPI, UpcomingDelivery, Order } from "@/lib/api";

export default function DashboardPage() {
  const [storeName, setStoreName] = useState("");
  const [deliveries, setDeliveries] = useState<UpcomingDelivery[]>([]);
  const [orders, setOrders] = useState<Order[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const router = useRouter();

  useEffect(() => {
    const token = localStorage.getItem("authToken");
    const storedStoreName = localStorage.getItem("storeName");

    if (!token || !storedStoreName) {
      router.push("/login");
      return;
    }

    setStoreName(storedStoreName);
    loadDashboardData();
  }, [router]);

  const loadDashboardData = async () => {
    try {
      setLoading(true);
      const [deliveriesResponse, ordersResponse] = await Promise.all([
        deliveriesAPI.getUpcomingDeliveries(),
        ordersAPI.getOrders(),
      ]);

      setDeliveries(deliveriesResponse.data);
      setOrders(ordersResponse.data);
    } catch (error: any) {
      if (error.response?.status === 401) {
        localStorage.removeItem("authToken");
        localStorage.removeItem("storeName");
        localStorage.removeItem("clientId");
        router.push("/login");
      } else {
        setError("Failed to load dashboard data. Please try again.");
      }
    } finally {
      setLoading(false);
    }
  };

  if (loading) {
    return (
      <div className="min-h-screen bg-gray-50 flex items-center justify-center">
        <div className="text-center">
          <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-blue-600 mx-auto"></div>
          <p className="mt-4 text-gray-600">Loading dashboard...</p>
        </div>
      </div>
    );
  }

  if (error) {
    return (
      <div className="min-h-screen bg-gray-50 flex items-center justify-center">
        <div className="text-center">
          <p className="text-red-600 mb-4">{error}</p>
          <button
            onClick={loadDashboardData}
            className="px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700"
          >
            Retry
          </button>
        </div>
      </div>
    );
  }

  const sampleDeliveries = [
    { title: "Frames", week: "24" },
    { title: "External Doors", week: "24" },
    { title: "Internal Doors", week: "25" },
    { title: "Window Factory", week: "24" },
    { title: "Project Doors", description: "Painted", week: "31" },
    { title: "Project Doors", description: "Laminated / Veneered", week: "36" },
    { title: "Akacija", description: "Forte and Pine Doors", week: "27" },
  ];

  return (
    <div className="min-h-screen bg-gray-50">
      <Header storeName={storeName} />
      <main className="container mx-auto px-4 py-8">
        <div className="space-y-8">
          <UpcomingDeliveries currentWeek={33} deliveries={sampleDeliveries} />
          <OrdersTable orders={orders} />
        </div>
      </main>
    </div>
  );
}
