# :hotel::heavy_plus_sign: ECSE223 AssetPlus Project

Team 5 - ECSE 223 - Model-Based Programming at McGill University

## Team Members

| Team Member   | GitHub username           |
| ------------- | ------------------------- |
| Team Member A | [Dramocrystal](https://github.com/Dramocrystal) |
| Team Member B | [sdotlee24](https://github.com/sdotlee24)       |
| Team Member C | [JPGC04](https://github.com/JPGC04)             |
| Team Member D | [AlanBrotherton](https://github.com/AlanBrotherton) |
| Team Member E | [justsom1-nizar](https://github.com/justsom1-nizar) |
| Team Member F | [plain-noodle-expert](https://github.com/plain-noodle-expert) |
| Team Member G | [SpeakLouderplz](https://github.com/SpeakLouderplz) |

## Project Overview

This repository hosts the codebase for the AssetPlus application, developed by Team 5 as part of ECSE 223 at McGill University.

## Implementation Details
- The application is implemented in Java, utilizing a suitable framework for the user interface.
- The UI framework is implemented in JavaFX.
- The chosen UI framework/technology is integrated into the automated build script using Gradle.
- Umple was used for the model.
- The app was implemented following the Model–View–Controller (MVC) pattern.

## Additional Technical Details
- **Umple Modeling Language:**
  - Umple was used to create the model for the application.

- **Design Pattern:**
  - The application follows the Model–View–Controller (MVC) architectural pattern.

- **Testing:**
  - Gherkin tests are employed to ensure the application meets specified requirements and behaves as expected.

- **State Machine:**
  - The application utilizes state machines to model and manage various states of tickets within the system.

- **Persistence Layer:**
  - A persistence layer is incorporated to handle data storage and retrieval, ensuring information is retained across sessions.

## Application Description
The AssetPlus application is designed for hotel staff to efficiently manage hotel assets, including furniture, appliances, and maintenance tasks within the hotel premises. Here's a brief overview of its key features:

- **User Accounts:**
  - The application includes a built-in user account for the hotel manager (manager@ap.com).
  - Employees use a company email address ending with @ap.com for their accounts.
  - Guests also provide an email address and a password for registration.

- **Asset Management:**
  - The manager specifies hotel assets, noting details such as purchase date, expected lifespan, and location within the hotel.
  - Each asset is assigned a unique asset number.

- **Maintenance Tracking:**
  - The application keeps track of maintenance history for each asset.
  - Users (manager, guests, and employees) can open maintenance tickets, linking them to specific assets.
  - Users can track the progress of their maintenance tickets using ticket numbers.

- **Task Assignment and Priority:**
  - The manager reviews and assigns open tickets to maintenance staff.
  - Both managers and employees can be assigned maintenance tasks.
  - Priority levels include urgent, normal, and low, with defined time estimates for resolution.

- **Manager Approval Workflow:**
  - The manager can specify whether a maintenance ticket requires approval upon resolution.
  - Maintenance notes document ticket progress, and the manager can approve or reject the fix.
