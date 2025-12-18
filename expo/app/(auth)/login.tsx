import {getToken, saveToken} from "@/lib/token";
import { useState } from "react";
import { View, Text, TextInput, Pressable, ActivityIndicator } from "react-native";
import { useRouter } from "expo-router";

const API_BASE_URL = "http://192.168.1.10:8080"; // ⚠️ à adapter (voir note plus bas)

export default function LoginScreen() {
    const router = useRouter();

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);

    const canSubmit = username.trim().length > 0 && password.length > 0 && !loading;

    const handleLogin = async () => {
        setError(null);
        setLoading(true);

        try {
            // ✅ 1) Appel backend (à brancher sur ton endpoint /auth/login)
            const res = await fetch(`${API_BASE_URL}/auth/login`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({username, password}),
            });

            if (!res.ok) {
                // message simple (tu peux améliorer selon ton backend)
                throw new Error("Identifiants invalides");
            }

            // ✅ 2) On attend un JSON du type: { token: "..." }
            const data: { token: string } = await res.json();
            console.log(data.token);

            // ✅ 3) Stockage sécurisé
            await saveToken("auth-token",data.token);

            // ✅ 4) Retour au guard (app/index.tsx)
            router.replace("/");
        } catch (e: any) {
            setError(e?.message ?? "Erreur de connexion");
        } finally {
            setLoading(false);
        }
    };

    return (
        <View style={{ flex: 1, justifyContent: "center", padding: 20, gap: 12 }}>
            <Text style={{ fontSize: 26, fontWeight: "600" }}>Connexion</Text>

            <TextInput
                placeholder="Nom d'utilisateur"
                autoCapitalize="none"
                value={username}
                onChangeText={setUsername}
                style={{
                    borderWidth: 1,
                    borderColor: "#ccc",
                    borderRadius: 10,
                    padding: 12,
                }}
            />

            <TextInput
                placeholder="Mot de passe"
                secureTextEntry
                value={password}
                onChangeText={setPassword}
                style={{
                    borderWidth: 1,
                    borderColor: "#ccc",
                    borderRadius: 10,
                    padding: 12,
                }}
            />

            {error ? <Text style={{ color: "red" }}>{error}</Text> : null}

            <Pressable
                onPress={handleLogin}
                disabled={!canSubmit}
                style={{
                    padding: 14,
                    borderRadius: 10,
                    alignItems: "center",
                    opacity: canSubmit ? 1 : 0.5,
                    backgroundColor: "black",
                }}
            >
                {loading ? (
                    <ActivityIndicator />
                ) : (
                    <Text style={{ color: "white", fontWeight: "600" }}>Se connecter</Text>
                )}
            </Pressable>
        </View>
    );
}
