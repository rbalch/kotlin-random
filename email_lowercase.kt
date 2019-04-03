import java.io.BufferedReader
import java.io.FileReader
import java.io.FileWriter

fun fixLine(line:String, counter:Int): String {
    val row: MutableList<String> = line.split(",").toMutableList()
    row.add(0, counter.toString())
    row.add(3, row[2].toLowerCase())
    return row.joinToString(",")
}

fun main() {
    var fileReader: BufferedReader
    var fileWriter: FileWriter = FileWriter("user_profiles_2.csv")
    var line: String?
    var counter: Int = 0
    
    fileReader = BufferedReader(FileReader("user_profiles.csv"))
    line = fileReader.readLine()
    while (line != null) {
        val row = fixLine(line, counter)
        if (counter % 100000 == 0) {
            println(counter);
        }
        fileWriter.append(row)
        fileWriter.append('\n')
        line = fileReader.readLine()
        counter++
    }
    
    fileReader.close()
    fileWriter.flush()
    fileWriter.close()
}
