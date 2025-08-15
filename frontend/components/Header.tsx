'use client';

import { LogOut } from 'lucide-react';
import { authAPI } from '@/lib/api';
import { useRouter } from 'next/navigation';

interface HeaderProps {
  storeName: string;
}

export default function Header({ storeName }: HeaderProps) {
  const router = useRouter();

  const handleLogout = () => {
    authAPI.logout();
    router.push('/login');
  };

  return (
    <header className="bg-primary-600 text-white px-6 py-4 shadow-md">
      <div className="flex justify-between items-center">
        <h1 className="text-xl font-semibold">Customer Order Portal</h1>
        <div className="flex items-center gap-4">
          <span className="text-sm">{storeName}</span>
          <button
            onClick={handleLogout}
            className="flex items-center gap-2 px-3 py-1 text-sm hover:bg-primary-700 rounded transition-colors"
          >
            <LogOut size={16} />
            Logout
          </button>
        </div>
      </div>
    </header>
  );
}
