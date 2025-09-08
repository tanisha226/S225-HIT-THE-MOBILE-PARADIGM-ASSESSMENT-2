# Muurrbuy Information App - Week 5 Project

## Project Overview
This Android application is designed to augment the existing Muurrbuy website (https://muurrbay.org.au/) by providing an interactive mobile experience. The app serves as an information platform with additional functionality for newsletter registration, community engagement, and event management.

## Course Information
- **Course:** S225 HIT238 THE MOBILE PARADIGM
- **Assessment:** Assessment 2 Individual coding
- **Project Type:** Information App - Initial App

## App Features

### 1. Core Information Display
- Primary information page showcasing Muurrbuy content
- Responsive design optimized for mobile devices
- Clean, intuitive user interface

### 2. Newsletter Registration
- User-friendly email input form
- Async processing with visual feedback
- Local storage of subscriber information
- Real-time subscriber count display

### 3. Community Membership
- Simple name-based registration
- Community member tracking
- Async processing for better user experience
- Member count display

### 4. Calendar of Events
- Event creation with title, date, and description
- Editable event management
- Local storage of event data
- Dynamic event display with animations

## Technical Implementation

### Advanced Features Implemented

#### 1. Promises, Async and Await
- **CompletableFuture.runAsync()** for background operations
- Simulated API calls with Thread.sleep() for realistic delays
- Main thread UI updates using Handler and Looper

#### 2. Layout Features and UI Design
- **ConstraintLayout** for responsive design
- **Material Design** color scheme and elevation
- **Card-based layout** with proper spacing and padding
- **Responsive scrolling** with ScrollView

#### 3. Performance Optimization
- **ExecutorService** for background thread management
- **Async operations** to prevent UI blocking
- **Efficient view updates** with minimal redraws

#### 4. Touch and Motion Events
- **Swipe gesture detection** (up/down)
- **Touch event handling** for enhanced user interaction
- **Gesture threshold management** for accurate detection

#### 5. Local Storage
- **SharedPreferences** for persistent data storage
- **Data serialization** using string concatenation
- **Automatic data loading** on app startup

#### 6. Animation and Visual Feedback
- **Fade-in animations** for new events
- **Status message animations** with auto-hide
- **Smooth transitions** between UI states

## Project Structure

### Java Classes
- **MainActivity.java** - Authentication and main entry point
- **Activity2.java** - Enhanced with logout functionality and line generation testing
- **InformationAppActivity.java** - Core information app with all features

### Layout Files
- **activity_main.xml** - Login interface with Information App button
- **activity_2.xml** - Enhanced second activity with logout
- **information_app_activity.xml** - Comprehensive information app layout

### Test Files
- **LineGenerationTest.java** - Unit tests for line generation functionality

## Requirements Fulfillment

### Part 1: Required Changes ✅
1. **IT Support Login Type** - Added to user type spinner
2. **Logout Button** - Implemented in Activity2 with return to front page
3. **Line Generation Testing** - Added verification and testing framework

### Part 2: Prototype App ✅
- **User Story:** Information management app for Muurrbuy community
- **Advanced Features:** Async operations, touch events, local storage, animations
- **UI Design:** Modern, responsive interface with Material Design principles

### Part 3: Report Summary ✅
- **Background:** Information app augmenting existing Muurrbuy website
- **Implementation:** Advanced Android features with async processing
- **UI Features:** Newsletter, community, and calendar management interfaces

## Installation and Usage

### Prerequisites
- Android Studio Arctic Fox or later
- Android SDK API 21+
- Java 8 or later

### Build Instructions
1. Clone the repository
2. Open project in Android Studio
3. Sync Gradle files
4. Build and run on device/emulator

### Testing
- Run `LineGenerationTest.java` for line generation verification
- Test async operations with network simulation
- Verify local storage persistence across app restarts

## User Experience Features

### Interactive Elements
- **Swipe down:** Refresh content status
- **Swipe up:** Hide status messages
- **Touch feedback:** Visual responses to user interactions
- **Async feedback:** Loading states and success messages

### Data Persistence
- Newsletter subscribers saved locally
- Community members tracked persistently
- Events stored and displayed across sessions
- Automatic data restoration on app launch

## Future Enhancements

### Potential Improvements
- **Geolocation API** integration for location-based features
- **Camera/Microphone** integration for media content
- **IndexDB** implementation for more complex data storage
- **PWA capabilities** for web app conversion
- **Real-time synchronization** with Muurrbuy website

### Scalability Considerations
- **Database migration** from SharedPreferences to Room/SQLite
- **API integration** with Muurrbuy backend services
- **Push notifications** for newsletter and event updates
- **Offline mode** with data synchronization

## Technical Architecture

### Design Patterns
- **MVC Architecture** with clear separation of concerns
- **Async Task Management** using CompletableFuture
- **Event-Driven UI** with onClick and onTouch handlers
- **Data Persistence Layer** with SharedPreferences

### Performance Considerations
- **Background Threading** for heavy operations
- **UI Thread Safety** with Handler-based updates
- **Memory Management** with proper lifecycle handling
- **Efficient View Updates** with minimal redraws

## Conclusion

This Information App successfully demonstrates advanced Android development concepts while providing a practical solution for the Muurrbuy community. The implementation showcases modern Android development practices including async programming, touch event handling, local storage, and responsive UI design.

The app serves as a foundation for future development and can be easily extended with additional features such as geolocation, media integration, and advanced database operations as outlined in the course requirements.


# How to Setup/Run Project

### Prerequisites

#### System Requirements
- *Operating System:* Windows 10/11, macOS 10.14+, or Linux Ubuntu 18.04+
- *RAM:* Minimum 8GB (16GB recommended)
- *Storage:* At least 4GB free space for Android Studio and SDK

#### Software Requirements
- *Android Studio:* Arctic Fox (2020.3.1) or later
  - Download from: https://developer.android.com/studio
- *Android SDK:* API Level 28+ (Android 9.0+)
- *Java Development Kit:* JDK 8 or later
- *Gradle:* Version 8.0+ (included with Android Studio)

#### Device Requirements
- *Physical Device:* Android 9.0 (API 28) or later
- *Emulator:* Android Virtual Device (AVD) with API 28+
- *USB Debugging:* Enabled on physical devices

### Step-by-Step Setup Instructions

#### 1. Clone the Repository
bash
git clone <repository-url>
cd Week_5


#### 2. Open Project in Android Studio
1. Launch Android Studio
2. Select "Open an existing Android Studio project"
3. Navigate to the Week_5 folder and select it
4. Wait for Android Studio to index the project

#### 3. Configure Android SDK
1. Go to *File → Project Structure → SDK Location*
2. Ensure Android SDK is properly configured
3. Install required SDK platforms:
   - Android 14 (API 34) - Target SDK
   - Android 9.0 (API 28) - Minimum SDK
4. Install required build tools and support libraries

#### 4. Sync Gradle Files
1. Android Studio will automatically prompt to sync
2. Click "Sync Now" or go to *File → Sync Project with Gradle Files*
3. Wait for sync to complete (may take a few minutes on first run)

#### 5. Build the Project
1. Go to *Build → Make Project* (Ctrl+F9)
2. Ensure build completes without errors
3. Check the "Build" tab for any warnings or issues

### Running the Application

#### Option 1: Run on Physical Device
1. *Enable Developer Options:*
   - Go to Settings → About Phone
   - Tap "Build Number" 7 times
   - Go back to Settings → Developer Options
   - Enable "USB Debugging"

2. *Connect Device:*
   - Connect device via USB cable
   - Allow USB debugging when prompted
   - Verify device appears in Android Studio's device list

3. *Run the App:*
   - Click the "Run" button (green play icon)
   - Select your connected device
   - Wait for app to install and launch

#### Option 2: Run on Emulator
1. *Create AVD:*
   - Go to *Tools → AVD Manager*
   - Click "Create Virtual Device"
   - Select a device (e.g., Pixel 4)
   - Choose system image (API 28+)
   - Configure AVD settings and click "Finish"

2. *Start Emulator:*
   - Click the play button next to your AVD
   - Wait for emulator to boot completely

3. *Run the App:*
   - Click the "Run" button
   - Select the running emulator
   - App will install and launch automatically