import { getToken } from "@/lib/auth";

const API_BASE_URL = "http://192.168.1.10:8080"; // adapte (téléphone = IP du PC)

export async function apiFetch(path: string, options: RequestInit = {}) {
    const token = await getToken('auth');

    const headers = {
        ...(options.headers ?? {}),
        ...(token ? { Authorization: `Bearer ${token}` } : {}),
        "Content-Type": "application/json",
    };

    const res = await fetch(`${API_BASE_URL}${path}`, { ...options, headers });
    if (!res.ok) throw new Error(`HTTP ${res.status}`);
    return res.json();
}
