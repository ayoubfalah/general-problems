import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;

public class FileUtility
{
   public static void copy(Path srcFile, Path destFile)
   {
     try
     {
       byte[] readBytes = Files.readAllBytes(srcFile);
       Files.write(destFile, readBytes);
     }catch(IOException e)
     {
       System.err.println(e.toString());
     }
   }
}
