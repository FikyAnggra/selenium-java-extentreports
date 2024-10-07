package utils;

public class Message {

    public static void errorMessage(String message) {
        String messages = message;
        String errorMessage = "";

        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace.length > 3) { // Mulai dari indeks 2 untuk melewati baris ini dan pemanggilannya
            String className = stackTrace[3].getClassName(); // Nama kelas
            String methodName = stackTrace[3].getMethodName(); // Nama metode
            String fileName = stackTrace[3].getFileName(); // Nama file
            int lineNumber = stackTrace[3].getLineNumber(); // Nomor baris
            errorMessage += "at " + className + "." + methodName + "(" + fileName + ":" + lineNumber + ")";
        }

        ExtentReport.statusFailed(message+"<br><br>"+errorMessage);
        ExtentReport.endReporting();
    }
}
