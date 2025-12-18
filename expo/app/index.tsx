import { Redirect } from 'expo-router';
import { useEffect, useState } from 'react';
import { View, ActivityIndicator, Text } from 'react-native';
import { getToken } from "@/lib/token";


export default function Index() {
    const [loggedIn, setLoggedIn] = useState<boolean | null>(null);

    useEffect(() => {
        async function checkLogin() {
            //await new Promise(r => setTimeout(r, 2000));
            const token = await getToken("auth-token");
            setLoggedIn(!!token);
        }
        checkLogin();
    }, []);
    console.log("token index: ", loggedIn);

    if (loggedIn === null) {
        // Pendant qu'on lit le token : petit écran de chargement
        return (
            <View style={{ flex: 1, justifyContent: "center", alignItems: "center" }}>
                <ActivityIndicator />
            </View>
        );
    }
    return loggedIn ? <Redirect href="/(tabs)/home" /> : <Redirect href="/(auth)/login" />;
}