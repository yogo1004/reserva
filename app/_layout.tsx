import { DarkTheme, DefaultTheme, ThemeProvider } from '@react-navigation/native';
import { useEffect, useState } from "react";
import { Stack} from 'expo-router';
import { StatusBar } from 'expo-status-bar';
import 'react-native-reanimated';
import * as SecureStore from "expo-secure-store";
import { Text } from 'react-native';

import { useColorScheme } from '@/hooks/use-color-scheme';

export const unstable_settings = {
  anchor: '(tabs)',
};

export default function RootLayout() {

  const colorScheme = useColorScheme();
    const [loggedIn, setLoggedIn] = useState<boolean | null>(null);

      useEffect(() => {
        async function checkLogin() {
          const token = await SecureStore.getItemAsync("token");
          setLoggedIn(!!token);
        }
        checkLogin();
      }, []);


    if (loggedIn === null) {
      return <Text>Chargement...</Text>;
    }


  return (
    <ThemeProvider value={colorScheme === 'dark' ? DarkTheme : DefaultTheme}>
      <Stack  screenOptions={{ headerShown: false }}>
       {loggedIn ? (
           <>
        <Stack.Screen name="(tabs)" options={{ headerShown: false }} />
        <Stack.Screen name="modal" options={{ presentation: 'modal', title: 'Modal' }} />

           </>
        ) : (
            <Stack.Screen name="(auth)" />

            )}
      </Stack>
      <StatusBar style="auto" />
    </ThemeProvider>
  );
}
