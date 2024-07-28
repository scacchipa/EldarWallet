# EldarWallet

# Application Architecture

The application has a three-layer architecture:
1. User interface with ViewModel,
2. Use cases where domain logic is found,
3. Data layer with repositories and all services such as databases, data encryption, etc.

## Application Usage

When the application is started for the first time, a button bar is seen at the top of the screen:
1. Login
2. Main
3. Card
4. QR code
5. Pay

Initially, all buttons will be disabled except the login button. All buttons will remain disabled until the user successfully logs into the application.

### A) Login Screen

- **Register Button**: This button saves user data (First Name, Family Name, UserName, and Password). To register a new user, all fields must be completed.
- **Unregister Button**: This button deletes the previously registered user data. To delete a registered user, the correct username and password must be entered.
- **Login Button**: This button logs in a previously registered user. To log in correctly, only the username and password fields need to be completed. Once the user logs in, all the buttons in the button bar are activated.
- **LogOut Button**: This button logs out the user. Once the user logs out, only the login screen remains active, and the rest are deactivated.

### 2) Main Screen

On this screen, a list of registered credit cards and the account balance are displayed. All new accounts start with $0 and will have a negative balance when transfers are made.

### 3) Card Screen

On this screen, a list of credit cards is displayed, and more cards can be registered in the user's name.
To register a new card, the following fields must be completed:
- **Owner**: FirstName + SPACE + FamilyName (this value is set automatically)
- **Card Number**: A series of numbers. If the first number is 3, 4, or 5, an icon indicates the card's brand.
- **CVV**: A series of numbers.
- **Due Date**: The expiration date. No restrictions.

**IMPORTANT**: For a card to be registered, the first digit of the Card Number must match the first digit of the CVV.

### 4) QR Screen

When the "Get QR Code" button is pressed, it makes a request to a QR code server and displays the code on the screen.

### 5) Pay Screen

The application makes a payment through the NFC-Repository (not implemented) and updates the credit card balance by deducting the amount.

To make a payment, all fields must be completed, and a credit card must be selected.
