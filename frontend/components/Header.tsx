"use client";

import { LogOut } from "lucide-react";
import { authAPI } from "@/lib/api";
import { useRouter } from "next/navigation";

interface HeaderProps {
  storeName: string;
}

export default function Header({ storeName }: HeaderProps) {
  const router = useRouter();

  const handleLogout = () => {
    authAPI.logout();
    router.push("/login");
  };

  return (
    <header className="bg-primary-600 text-white px-4 sm:px-6 py-3 sm:py-4 shadow-md">
      <div className="flex justify-between items-center">
        <h1 className="text-lg sm:text-xl font-semibold truncate mr-2">
          Customer Order Portal
        </h1>
        <div className="flex items-center gap-2 sm:gap-4 flex-shrink-0">
          <span className="text-xs sm:text-sm truncate max-w-[120px] sm:max-w-none">
            {storeName}
          </span>
          <button
            onClick={handleLogout}
            className="flex items-center gap-1 sm:gap-2 px-2 sm:px-3 py-1 text-xs sm:text-sm hover:bg-primary-700 rounded transition-colors whitespace-nowrap"
          >
            <LogOut size={14} className="sm:w-4 sm:h-4" />
            <span className="hidden sm:inline">Logout</span>
            <span className="sm:hidden">Out</span>
          </button>
        </div>
      </div>
    </header>
  );
}
