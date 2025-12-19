import { useEffect, useState } from "react";
import { View, Text, FlatList, ActivityIndicator, RefreshControl } from "react-native";
import { apiFetch } from "@/lib/api";
import {data} from "browserslist";
import {random} from "nanoid";

type Room = {
    id: number;
    name: string;
    location: string | null;
    capacity: number;
    is_active: boolean;
};

export default function RoomsScreen() {
    const [rooms, setRooms] = useState<Room[]>([]);
    const [loading, setLoading] = useState(true);
    const [refreshing, setRefreshing] = useState(false);
    const [error, setError] = useState<string | null>(null);

    const loadRooms = async () => {
        setError(null);
        const data = await apiFetch<Room[]>("/api/rooms");
        setRooms(data);
    };



    useEffect(() => {
        console.log("rooms state updated:", rooms.map(r => ({ id: r.id, is_active: r.is_active })));
        (async () => {
            try {
                await loadRooms();
            } catch (e: any) {
                setError(e?.message ?? "Erreur chargement salles");
            } finally {
                setLoading(false);
            }
        })();
    }, []);

    const onRefresh = async () => {
        setRefreshing(true);
        try {
            await loadRooms();
        } catch (e: any) {
            setError(e?.message ?? "Erreur refresh");
        } finally {
            setRefreshing(false);
        }

        console.log(rooms);
    };

    if (loading) {
        return <ActivityIndicator style={{ marginTop: 40 }} />;
    }

    return (
        <View style={{ flex: 1, padding: 16 }}>
            <Text style={{ fontSize: 26, fontWeight: "600", marginBottom: 12 }}>
                Salles
            </Text>

            {error ? (
                <Text style={{ color: "red", marginBottom: 10 }}>{error}</Text>
            ) : null}

            <FlatList
                data={rooms}
                keyExtractor={(item) => String(item.id)}
                refreshControl={<RefreshControl refreshing={refreshing} onRefresh={onRefresh} />}
                ItemSeparatorComponent={() => <View style={{ height: 10 }} />}
                renderItem={({ item } ) =>


                    (
                    <View
                        style={{
                            borderWidth: 1,
                            borderColor: "#ddd",
                            borderRadius: 12,
                            padding: 14,
                        }}
                    >
                        <Text style={{ fontSize: 18, fontWeight: "600" }}>{item.name} ({item.capacity}) - {item.is_active ? "libre" : "occupe"}</Text>


                    </View>
                )}
                ListEmptyComponent={
                    <Text style={{ color: "#666" }}>Aucune salle trouvée.</Text>
                }
            />
        </View>
    );
}
