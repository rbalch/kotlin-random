import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.apache.commons.csv.CSVPrinter
import org.apache.commons.csv.CSVRecord

fun fixLine(row: CSVRecord): MutableList<String> {
    val result = mutableListOf<String>()
    for (i in 0 until row.size()) {
        val col = row.get(i)
        if (i == 0) {
            result.add(row.recordNumber.toString())
        }
        result.add(col)
        if (i == 1) {
            result.add(col.toLowerCase())
        }
    }
    return result
}

fun main() {
    var fileReader = BufferedReader(FileReader("user_profiles.csv"))
    val fileWriter = BufferedWriter(FileWriter("user_profiles_3.csv"))
    val csvParser = CSVParser(fileReader, CSVFormat.DEFAULT)
    val csvPrinter = CSVPrinter(fileWriter, CSVFormat.DEFAULT)
    
    for (csvRecord in csvParser) {
        val newRow = fixLine(csvRecord)
        csvPrinter.printRecord(newRow)
        if (csvRecord.recordNumber.toInt() % 100000 == 0) {
            println(csvRecord.recordNumber)
        }
    }
    
    fileReader.close()
    fileWriter.flush()
    fileWriter.close()
}

main()
// kotlinc -script email_lowercase.kts -cp commons-csv-1.6.jar
