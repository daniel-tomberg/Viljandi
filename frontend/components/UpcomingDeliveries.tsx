"use client";

interface DeliveryItem {
  title: string;
  description?: string;
  week: string;
}

interface UpcomingDeliveriesProps {
  currentWeek: number;
  deliveries: DeliveryItem[];
}

export default function UpcomingDeliveries({
  currentWeek,
  deliveries,
}: UpcomingDeliveriesProps) {
  return (
    <>
      <div className="mb-6">
        <div className="flex justify-between items-start mb-2">
          <h2 className="text-xl font-semibold text-gray-800">
            Upcoming Deliveries
          </h2>
          <span className="text-sm text-gray-600">
            Current Week: {currentWeek}
          </span>
        </div>
        <p className="text-sm text-gray-600">
          The delivery time will be confirmed later. The order will be produced
          in this week.
        </p>
      </div>

      <div className="flex gap-4 ">
        {deliveries.map((delivery, index) => (
          <div
            key={index}
            className="bg-white rounded-lg shadow-md hover:shadow-lg transition-shadow duration-200 hover:scale-105 transform transition-transform duration-200 p-4 min-w-[160px] flex-shrink-0 cursor-pointer flex flex-col"
          >
            <h3 className="font-semibold text-gray-800 mb-2">
              {delivery.title}
            </h3>
            {delivery.description && (
              <p className="text-sm text-gray-600 mb-2">
                {delivery.description}
              </p>
            )}
            <p className="text-sm font-medium text-gray-700 mt-auto">
              Week {delivery.week}
            </p>
          </div>
        ))}
      </div>
    </>
  );
}
