package cinema

fun main() {
    println("Enter the number of rows:")
    val userInputRows = readlnOrNull()?.toIntOrNull()
    println("Enter the number of seats in each row:")
    val userInputSeats = readlnOrNull()?.toIntOrNull()
    println()

    if (userInputRows == null || userInputSeats == null || userInputRows > 9 || userInputSeats > 9) {
        println("Invalid input for rows or seats.")
        return
    }

    // Initialize seats array, 'S' for seat available, 'B' for booked
    val seats = Array(userInputRows) { CharArray(userInputSeats) { 'S' } }

    showMenu()

    var userSelection: Int?
    var userSelectedRow = 0
    var userSelectedSeat = 0
    var numberOfTickets = 0
    var ticketsSoldAtTen =0
    var tickersSoldAtEight = 0
    var currentIncome = 0

    do {
        userSelection = readlnOrNull()?.toIntOrNull()

        when (userSelection) {
            1 -> {
                seatArrangement(seats)
                println()
                showMenu()
            }

            2 -> {
                var validSelection = false
                while (!validSelection) {
                    println()
                    println("Enter a row number:")
                    userSelectedRow = readlnOrNull()?.toIntOrNull() ?: continue
                    println("Enter a seat number in that row:")
                    userSelectedSeat = readlnOrNull()?.toIntOrNull() ?: continue

                    if (userSelectedRow in 1..userInputRows && userSelectedSeat in 1..userInputSeats) {
                        if (seats[userSelectedRow - 1][userSelectedSeat - 1] == 'S') {
                            seats[userSelectedRow - 1][userSelectedSeat - 1] = 'B'
                            val seatsCount = userInputRows * userInputSeats
                            val ticketPrice = calculateTicketPrice(seatsCount, userInputRows, userSelectedRow)
                            println("Ticket Price: $$ticketPrice")
                            numberOfTickets++
                            if(ticketPrice ==10){
                                ticketsSoldAtTen++
                            }else{
                                tickersSoldAtEight ++
                            }
                            currentIncome =( ticketsSoldAtTen * 10) + (tickersSoldAtEight * 8)
                            validSelection = true

                        } else {
                            println("That ticket has already been purchased!")
                            println()

                        }
                    } else {
                        println("Wrong input!")
                        println()

                    }
                }
                println()
                showMenu()
            }

            3 -> {
                var totalIncome = calculateTotalIncome(userInputRows, userInputSeats)
                var totalSeats = userInputSeats * userInputRows
                val percentage = ((numberOfTickets / totalSeats.toDouble()) * 100)
                val formatPercentage = "%.2f".format(percentage)
                println()
                println("Number of purchased tickets: $numberOfTickets")
                println("Percentage: $formatPercentage%")
                println("Current income: $$currentIncome")
                println("Total income: $$totalIncome")
                println()
                showMenu()

            }

            0 -> break
            else -> println("Please enter 1, 2, 3 or 0.")
        }
    } while (true)
}

// Modify seatArrangement to use the seats array
fun seatArrangement(seats: Array<CharArray>) {
    println("Cinema:")
    print("  ")
    for (i in seats[0].indices) print("${i + 1} ")
    println()

    for (i in seats.indices) {
        print("${i + 1} ")
        for (j in seats[i].indices) {
            print("${seats[i][j]} ")
        }
        println()
    }
}

// Rest of the functions remain the same
fun calculateTicketPrice(seatsCount: Int, rows: Int, selectedRow: Int): Int {
    return if (seatsCount <= 60) {
        10
    } else {
        val firstHalf = rows / 2
        if (selectedRow <= firstHalf) 10 else 8
    }
}

fun showMenu() {
    println("1. Show the seats ")
    println("2. Buy a ticket")
    println("3. Statistics")
    println("0. Exit")

}

fun calculateTotalIncome(userInputRows: Int, userInputSeats: Int): Int {
    var totalIncome: Int
    val seatsCount = userInputRows * userInputSeats
    totalIncome = if (seatsCount <= 60) {
        seatsCount * 10
    } else {
        val firstHalf = userInputRows / 2
        val secondHalf = userInputRows - firstHalf
        (firstHalf * userInputSeats * 10) + (secondHalf * userInputSeats * 8)
    }
    return totalIncome

}



