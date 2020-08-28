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

## 1. Reality summary
The software is centered on **travels**. Each `travel` will have a `date`, an `origin`, a destination, one `car`, a number of passengers (including the driver) and can optionally have one or more **tolls**.

Each `toll` is going to charge a `car` only once, using the `car category` as a reference to know the fare.

Each `travel` will provide it `total cost`. This value will be made of the sum of all the `tolls` fares and the used `fuel`.
Also the _per-passenger_ `cost` will be possible to obtain dividing the `total cost` in **equal parts** between the `passengers quantity`.

## 2. Entity relationship diagram
## 3. Prerequisites
## 4. How to use
## 5. Notes
- The prices and categories were based on the ones used at my own country (Uruguay). These might be different on other countries or locations.
>### Sources
> - Fuel types:  [Administración Nacional de Combustibles Alcohol y Pórtland (ANCAP)](https://www.ancap.com.uy/2093/1/precios-combustibles.html)
> - Tolls costs and car categories: [Ministerio de trabajo y obras publicas (MTOP)](http://www.mtop.gub.uy/peajes)

## 6. Run demo
### Add car
### List cars
### Add toll
### List tolls
### Add travel