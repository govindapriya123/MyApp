import React, { useEffect, useState } from 'react';
import { View, Text, NativeModules } from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { enableScreens } from 'react-native-screens';
enableScreens(true);

const { SplashModule } = NativeModules;
const Stack = createNativeStackNavigator();
console.log(" SplashModule", SplashModule)

const HomeScreen = () => (
  <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
    <Text>Main App Screen</Text>
  </View>
);

export default function App() {
  useEffect(() => {
    SplashModule?.show();
    return () => SplashModule?.hide();
  }, []);
  
  
  return (
    <NavigationContainer>
      <Stack.Navigator>
        <Stack.Screen name="Home" component={HomeScreen} />
      </Stack.Navigator>
    </NavigationContainer>
  );
}
