import * as SecureStore from "expo-secure-store";



export async function saveToken(token_key: string, token: string) {
    await SecureStore.setItemAsync(token_key, token);
}

export async function getToken(token_key: string) {
    return await SecureStore.getItemAsync(token_key);
}

export async function removeToken(token_key: string) {
   await SecureStore.deleteItemAsync(token_key);
}