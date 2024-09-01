## Project Overview

This Android app uses Java and Kotlin, managed with Gradle. It includes user authentication and navigation between activities and fragments.

### Key Files

- `MainActivity.java`
- `SignInActivity.java`
- `NewActionActivity.java`
- `mingleDetailFragment.java`
- `res/layout/activity_main.xml`
- `res/layout/activity_sign_in.xml`
- `res/layout/activity_new_action.xml`
- `res/values/styles.xml`
- `res/values/themes.xml`
- `AndroidManifest.xml`

### MainActivity

- **Purpose**: Checks login status and redirects.
- **Key Methods**:
  - `onCreate()`: Initializes and checks login.
  - `isUserLoggedIn()`: Checks login status.

### SignInActivity

- **Purpose**: Manages user login.
- **Key Methods**:
  - `onCreate()`: Sets up login form.
  - `updateUiWithUser()`: Updates UI on login.
  - `showLoginFailed()`: Shows error on login failure.

### NewActionActivity

- **Purpose**: Placeholder for post-login action.
- **Key Methods**:
  - `onCreate()`: Initializes activity.

### mingleDetailFragment

- **Purpose**: Displays detailed info.
- **Key Methods**:
  - `onCreate()`: Loads content.
  - `onCreateView()`: Sets up view.
  - `updateContent()`: Updates displayed content.

### How to Run

1. **Clone**: `git clone https://github.com/melrs/mingle.git`
2. **Open**: In Android Studio.
3. **Build**: Use build option.
4. **Run**: On emulator or device.

### Non-Functional Requirements and/or Constraints

- [ ] User registration with profile separation
- [ ] Use of internal storage
- [ ] Use of at least two sensors and/or physical device resources
- [ ] Use of external data storage

### Functionalities
- [ ] Create account
- [ ] Log in
- [ ] Input bill details
- [ ] View bill details
- [ ] Edit bill details
- [ ] Split bill with friends
- [ ] Digitalize receipts
- [ ] View digitized receipts
- [ ] Edit user profile
- [ ] Bill splitting notifications
- [ ] Bill splitting history
- [ ] Friend search
- [ ] Data synchronization with the cloud
