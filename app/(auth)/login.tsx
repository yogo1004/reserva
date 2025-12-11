import { View, Text, Button } from "react-native";
import { useRouter } from "expo-router";
import * as SecureStore from "expo-secure-store";

export default function Login() {
  const router = useRouter();

  const handleLogin = async () => {

      await SecureStore.setItemAsync("token", "fakeToken");
      // plus tard : vérifier identifiers
    router.replace("/(tabs)");
  };

  return (
    <View style={{ flex: 1, justifyContent: "center", padding: 20 }}>
      <Text style={{ fontSize: 24, marginBottom: 20 }}>Connexion</Text>
      <Button title="Se connecter" onPress={handleLogin} />
    </View>
  );
}
