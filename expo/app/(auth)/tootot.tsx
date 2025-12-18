import { View, Text, Button } from "react-native";
import { useRouter } from "expo-router";
import {getToken, saveToken} from "@/lib/token";

export default function Index() {
  const router = useRouter();

  const handleLogin = async () => {

     //saveToken("faketoken")
     // console.log("token saved: " + await getToken());
      // plus tard : vérifier identifiers
   // router.replace("/(tabs)");
  };

  return (
    <View style={{ flex: 1, justifyContent: "center", padding: 20 }}>
      <Text style={{ fontSize: 24, marginBottom: 20 }}>Connexion</Text>
      <Button title="Se connecter" onPress={handleLogin} />
    </View>
  );
}
