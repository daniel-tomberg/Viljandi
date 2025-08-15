import axios from "axios";

const API_BASE_URL =
  process.env.NEXT_PUBLIC_API_URL || "http://localhost:8080/api";

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    "Content-Type": "application/json",
  },
});


api.interceptors.request.use((config) => {
  const token = localStorage.getItem("authToken");
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

export interface LoginRequest {
  username: string;
  password: string;
}

export interface LoginResponse {
  token: string;
  storeName: string;
  clientId: string;
}

export interface UpcomingDelivery {
  id: number;
  categoryName: string;
  additionalDescription?: string;
  deliveryWeek: number;
  clientId: string;
}

export interface Order {
  id: number;
  poNumber: string;
  orderDate: string;
  expectedDelivery: string;
  status: "Processing" | "Delivered" | "Cancelled";
  clientId: string;
}

export interface OrderLine {
  id: number;
  orderNum: number;
  line: number;
  productCode: string;
  eanCode: string;
  productName: string;
  qty: number;
  deliveryDate: string;
  poNumber: string;
}

export const authAPI = {
  login: (credentials: LoginRequest) =>
    api.post<LoginResponse>("/auth/login", credentials),

  logout: () => {
    localStorage.removeItem("authToken");
    localStorage.removeItem("storeName");
    localStorage.removeItem("clientId");
  },
};

export const deliveriesAPI = {
  getUpcomingDeliveries: () =>
    api.get<UpcomingDelivery[]>("/deliveries/upcoming"),
};

export const ordersAPI = {
  getOrders: () => api.get<Order[]>("/orders"),

  getOrderLines: (poNumber: string) =>
    api.get<OrderLine[]>(`/orders/${poNumber}/lines`),
};

export default api;
