# inventoryManagement
Senior Project with teammates Phillip Benjamin and Kyle Bess

# Setup for IntelliJ IDE
Add javafx-sdk-16 to libraries (can be found [directly here](https://gluonhq.com/download/javafx-16-sdk-windows/) or [here](https://gluonhq.com/products/javafx/)), specify JavaSE-15 as the project. Add the following command to VM under build configuration:
"--module-path C:\your\javafx\path\javafx-sdk-16/lib --add-modules=javafx.controls,javafx.fxml"

#### change the path to your javafx-sdk-16 directory
As an example: "C:\Users\Terrence\Documents\javafx-sdk-16/lib --add-modules=javafx.controls,javafx.fxml"

Rebuild the project, and it should run. If you run into any problems, please contact me.
