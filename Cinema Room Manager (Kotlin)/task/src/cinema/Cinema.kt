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
    do {
        userSelection = readlnOrNull()?.toIntOrNull()

        when (userSelection) {
            1 -> {
                seatArrangement(seats)
                println()
                showMenu()
            }

            2 -> {
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
                    } else {
                        println("This seat is already booked.")
                    }
                } else {
                    println("Invalid seat selection.")
                }
                println()
                showMenu()
            }

            0 -> break
            else -> println("Please enter 1, 2, or 0.")
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
    println("0. Exit")
    println()
}

//if (userInputRows != null && userInputSeats != null && userInputRows <= 9 && userInputSeats <= 9) {
//    val seatsCount = userInputRows * userInputSeats
//    seatArrangement(userInputRows, userInputSeats)
//
//    println("Enter a row number:")
//    val userSelectedRow = readlnOrNull()?.toIntOrNull()
//    println("Enter a seat number in that row:")
//    val userSelectedSeat = readlnOrNull()?.toIntOrNull()
//
//    if (userSelectedRow != null && userSelectedSeat != null) {
//        val ticketPrice = calculateTicketPrice(seatsCount, userInputRows, userSelectedRow)
//        println("Ticket Price: $$ticketPrice")
//        seatArrangement(userInputRows, userInputSeats, userSelectedRow, userSelectedSeat)
//    }
//}
