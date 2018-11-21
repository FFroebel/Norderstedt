//Serial Library
#include <SoftwareSerial.h>
//NeoPixel Library
#include <FastLED.h>

//Define Pins for Serial-Communication
#define SOFT_SERIAL_RX  9  //receive data, connect to TX on Bluefruit
#define SOFT_SERIAL_TX 10  //send data, connect to RX on Bluefruit

//set the Baudrate of softSerial-Communication
//see on the back of the Flora Bluefruit "u/9600 Baud UART"
#define BLE_BAUD_RATE  9600

// Set which pin the Signal output from the light sensor is connected to
int inputPin = 2; //S on light sensor
int inputPin2 = 8;


// Create the time
unsigned long time;
uint8_t current_state = 0;

bool BLE = false;
 

// Define Pin connected to NeoPixel-LED for Feedback
#define SLVL_PIN 5
#define NUM_LEDS_SLVL 1

#define SLVR_PIN 12
#define NUM_LEDS_SLVR 1

// set Brightness of NeoPixels-LED
#define BRIGHTNESS     30

// set softSerial connection to bluefruit
SoftwareSerial softSerial(SOFT_SERIAL_RX, SOFT_SERIAL_TX); // RX, TX
// set NeoPixel-LEDs for Status
CRGB ledsSL[NUM_LEDS_SLVL];
CRGB ledsSR[NUM_LEDS_SLVR];

int buttonState = 0; 
int buttonState2 = 0;  

void setup() {
  Serial.begin(9600);
  Serial.println("Hello World");
  
  softSerial.begin(BLE_BAUD_RATE);
  softSerial.println("Hello Soft World");

  // Set sensorPin for LightSensor as an INPUT
  pinMode(inputPin, INPUT);
  pinMode(inputPin2, INPUT);

//set gnd and power pin for ble
    pinMode(8, OUTPUT);
  digitalWrite(8, HIGH);
    pinMode(11, OUTPUT);
  digitalWrite(11, LOW);
  pinMode(3, OUTPUT);
  digitalWrite(3, LOW);
  pinMode(9, OUTPUT);
  digitalWrite(9, LOW);
    pinMode(13, OUTPUT);
  digitalWrite(13, LOW);
      pinMode(11, OUTPUT);
  digitalWrite(11, HIGH);
//  
  // Initializes the LEDs
  FastLED.addLeds<NEOPIXEL, SLVL_PIN>(ledsSL, NUM_LEDS_SLVL);
  FastLED.addLeds<NEOPIXEL, SLVR_PIN>(ledsSR, NUM_LEDS_SLVR);
}

void loop() { 
  Serial.print("Time: ");
  time = millis();
//  Serial.println(time);    //prints time since program started
//  delay(1000);
//  ledsSL[1] = CRGB::Black;
buttonState = digitalRead(inputPin);
buttonState2 = digitalRead(inputPin2);

//BLE connection on
  if (softSerial.available()){
      Serial.println("softSerial is available");
      char c = softSerial.read(); 
      Serial.println(c);
      BLE = true;
      
if (buttonState1 == LOW && buttonState2 == LOW) {
    // turn LED on:
    ledsSL[0] = CRGB::Black;
      FastLED.show();
      //negative Feedback
  }  else if (buttonState1 == HIGH && buttonState2 == LOW {
    ledsSL[0] = CRGB::Red;
      FastLED.show();
      //sends information A via ble to mobile app
      //negative Feedback
      Serial.write(A);
            
  } else if (buttonState1 == LOW && buttonState2 == HIGH {
    ledsSL[0] = CRGB::Green;
      FastLED.show();
      //sends information B via ble to mobile app
      //positive Feedback
  Serial.write(B);
  } 

//
  if (buttonState2 == HIGH) {
    // turn LED on:
    ledsSR[0] = CRGB::Black;
      FastLED.show();
  }  else {
    ledsSR[0] = CRGB::Green;
      FastLED.show();
      //delay(500);
  } 
  }
}
