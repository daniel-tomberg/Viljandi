import { Search } from "lucide-react";

interface FilterInputProps {
  placeholder: string;
  value: string;
  onChange: (value: string) => void;
  className?: string;
}

export default function FilterInput({
  placeholder,
  value,
  onChange,
  className = "w-full sm:w-56",
}: FilterInputProps) {
  return (
    <div className="relative inline-block w-full sm:w-auto">
      <input
        type="text"
        placeholder={placeholder}
        value={value}
        onChange={(e) => onChange(e.target.value)}
        className={`${className} pl-8 pr-3 py-2 sm:py-1 text-sm border border-[#404040] rounded-full focus:outline-none focus:ring-2 focus:ring-blue-500 text-black font-normal`}
      />
      <Search
        className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400 pointer-events-none"
        size={16}
      />
    </div>
  );
}
