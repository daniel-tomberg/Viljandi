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
      <div className="mb-4 sm:mb-6">
        <div className="flex flex-col sm:flex-row sm:justify-between sm:items-start gap-2 sm:gap-0 mb-2">
          <h2 className="text-lg sm:text-xl font-semibold text-gray-800">
            Upcoming Deliveries
          </h2>
          <span className="text-xs sm:text-sm text-gray-600">
            Current Week: {currentWeek}
          </span>
        </div>
        <p className="text-xs sm:text-sm text-gray-600">
          The delivery time will be confirmed later. The order will be produced
          in this week.
        </p>
      </div>

      <div className="flex gap-2 sm:gap-4 overflow-x-auto pb-2 sm:pb-0 scrollbar-hide">
        {deliveries.map((delivery, index) => (
          <div
            key={index}
            className="bg-white rounded-lg shadow-md hover:shadow-lg transition-shadow duration-200 hover:scale-105 transform transition-transform duration-200 p-3 sm:p-4 min-w-[140px] sm:min-w-[160px] flex-shrink-0 cursor-pointer flex flex-col"
          >
            <h3 className="font-semibold text-gray-800 mb-1 sm:mb-2 text-sm sm:text-base">
              {delivery.title}
            </h3>
            {delivery.description && (
              <p className="text-xs sm:text-sm text-gray-600 mb-1 sm:mb-2">
                {delivery.description}
              </p>
            )}
            <p className="text-xs sm:text-sm font-medium text-gray-700 mt-auto">
              Week {delivery.week}
            </p>
          </div>
        ))}
      </div>
    </>
  );
}
