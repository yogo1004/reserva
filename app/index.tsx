// app/index.tsx
import { Redirect } from 'expo-router';
import { useEffect, useState } from 'react';
import * as SecureStore from 'expo-secure-store';
import { Text } from 'react-native';

export default function Index() {
    const [loggedIn, setLoggedIn] = useState<boolean | null>(null);

    useEffect(() => {
        async function checkLogin() {
            const token = await SecureStore.getItemAsync('token');
            setLoggedIn(!!token);
        }
        checkLogin();
    }, []);

    if (loggedIn === null) {
        // Pendant qu'on lit le token : petit écran de chargement
        return <Text>Chargement...</Text>;
    }

    if (loggedIn) {
        // Utilisateur connecté → envoie vers les tabs
        return <Redirect href="/(tabs)" />;
    }

    // Pas connecté → envoie vers l'écran de login
    return <Redirect href="/(auth)/login" />;
}
