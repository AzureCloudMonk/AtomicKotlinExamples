// ErrorReporting/Postconditions.kt
import atomictest.*
import errorreporting.localFile

val resultFile = localFile("results.txt")

fun createResultFile(create: Boolean) {
  if (create)
    resultFile.writeText("Results")
  // ... other execution paths
  check(resultFile.exists(), {
    "${resultFile.getName()} doesn't exist!"
  })
}

fun main() {
  if (resultFile.exists())
    resultFile.delete()
  capture {
    createResultFile(false)
  } eq "IllegalStateException: " +
    "results.txt doesn't exist!"
  createResultFile(true)
}
