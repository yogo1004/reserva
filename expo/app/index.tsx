import { Redirect } from 'expo-router';
import { useEffect, useState } from 'react';
import { View, ActivityIndicator, Text } from 'react-native';
import { getToken, removeToken } from "@/lib/auth";


export default function Index() {
    const [loggedIn, setLoggedIn] = useState<boolean | null>(null);

    useEffect(() => {



    //    removeToken('auth');
    //    removeToken('token_auth');
    //    removeToken('auth_token');
    //    removeToken('auth-token');
    //    removeToken('token-auth');



        async function checkLogin() {
            //await new Promise(r => setTimeout(r, 2000));
            const token = await getToken("auth");
            setLoggedIn(!!token);
        }
        checkLogin();
    }, []);

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