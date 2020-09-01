# Car Trip Cost
> By: Federico De Luca  
>
> Mail: federicoNdeluca@gmail.com  
>
> LinkedIn: https://www.linkedin.com/in/federicoDL
>
> GitHub: https://github.com/TheKingArthas

## 0. Abstract
This _simple_ project was intended to refresh some _OOP_, design patterns and Java development knowledge. Also most of the development (excluding the _GUI_) was done using Test-Driven-Development.

The goal it's to achieve a software that calculates the _per-passenger_ cost of a car trip, using fuel and tolls costs.

>**IMPORTANT**
>
> Given the objective of the project, several simplifications of the scope of reality have been assumed.
>
>For example:
> - The distance between locations is linear
> - All tolls charge the same amount to each car category
> - There are no location or distance restrictions between the travel origin, destination, and asociated tolls
> - The distance unit used is kilometers (Km)
> - The tank capicty unit used is litres (L)
> - The efficiency unit used is kilometers per litre (Km/L)

## 1. Reality summary
The software is centered on **travels**. Each `travel` will have a `date`, an `origin`, a destination, one `car`, a number of passengers (including the driver) and can optionally have one or more **tolls**.

Each `toll` is going to charge a `car` only once, using the `car category` as a reference to know the fare.

Each `travel` will provide it `total cost`. This value will be made of the sum of all the `tolls` fares and the used `fuel`.
Also the _per-passenger_ `cost` will be possible to obtain dividing the `total cost` in **equal parts** between the `passengers quantity`.

## 2. UML
![UML](/Documentation/img/CarTripCost_UML.png)

## 3. How to use
### 3.0. Prerequisites
_Java Runtime Enviroment_ must be installed on the computer.
### 3.1 Run
1. To start the program on _Windows_ you should open a _command shell_ and type `java -jar JAR_FILE`
    >In my case: `java -jar C:\Users\Federico\Desktop\CarTripCost-v1.0.jar`
1. Enter `(1)` to select `Start program` and press enter.
1. The software will ask the current prices of fuel and tolls car categories.
1. After this values are entered, the main menu will be shown.

### 3.2. Add car
1. On the main menu select `(2) Car management`.
1. On the Car management menu select `(1) Add new car`.
1. You will be asked to enter the car data. If all your entered fiels are correct enter `Y`, or alternately you can enter `N` to start again.
1. If you want to see the entered car you can select again `(2) Car management` and then select `(2) List cars`.

### 3.3. Update fuel price
1. On the main menu select `(3) Fuel management`.
1. On the Car management menu select `(1) Update fuel price`.
1. Select the desired car category.
1. Enter the new price. If the changes are correct enter `Y`, or alternately you can enter `N` to start again.
1. If you want to see see the current prices you can select again `(3) Fuel  management` and then select `(2) List fuel prices`.

### 3.4. Update car category price
1. On the main menu select `(4) Toll management`.
1. On the Car management menu select `(3) Update category price`.
1. Select the desired fuel type.
1. Enter the new price. If the changes are correct enter `Y`, or alternately you can enter `N` to start again.
1. If you want to see see the current prices you can select again `(3) Toll  management` and then select `(4) List car categories prices`.

### 3.5. Add toll
1. On the main menu select `(4) Toll management`.
1. On the Car management menu select `(1) Add toll`.
1. You will be asked to enter the car data. If all your entered fiels are correct enter `Y`, or alternately you can enter `N` to start again.
1. If you want to see see the current prices you can select again `(3) Toll  management` and then select `(2) List tolls`.

### 3.6. Get travel price
1. On the main menu select `(1) New travel`.
1. You will be asked to enter the car data. If all your entered fiels are correct enter `Y`, or alternately you can enter `N` to start again.
1. Optionally after you confirm the entered data you will be available to add tolls to the travel. Enter the number of the desired toll and press enter. repeat this procedure until you add all desired tolls. 
1. Enter `0` and press enter to continue. The prices and other details will be shown on screen.

### 3.7. Demo data
If desired you will be able to load _demo data_ on the software. This option is available when you first run the program, selecting `(2) Run demo` instead of `(1) Start program`.

## 4. Notes
- The prices and categories were based on the ones used at my own country (Uruguay). These might be different on other countries or locations.
>### Sources
> - Fuel types:  [Administración Nacional de Combustibles Alcohol y Pórtland (ANCAP)](https://www.ancap.com.uy/2093/1/precios-combustibles.html)
> - Tolls costs and car categories: [Ministerio de trabajo y obras publicas (MTOP)](http://www.mtop.gub.uy/peajes)

## 5. Pending for future releases
- Exception management
- Field inputs validations
- Permanent persistence