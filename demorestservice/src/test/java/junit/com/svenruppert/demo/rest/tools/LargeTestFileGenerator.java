package junit.com.svenruppert.demo.rest.tools;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LargeTestFileGenerator {


  public static void generateLargeFile(Path largeFile, long fileSizeBytes)
      throws IOException {

    if (Files.exists(largeFile) && Files.size(largeFile) == fileSizeBytes) {
      return;
    }

    System.out.println("Generating test data file with 'AE' pattern...");
    try (var out = Files.newOutputStream(largeFile)) {
      byte[] pattern = new byte[] { 'A', 'E' }; // 0x41 0x45
      byte[] buffer = new byte[1024 * 1024]; // 1 MB
      for (int i = 0; i < buffer.length; i += 2) {
        buffer[i] = pattern[0];
        if (i + 1 < buffer.length) {
          buffer[i + 1] = pattern[1];
        }
      }

      long written = 0;
      while (written < fileSizeBytes) {
        int toWrite = (int) Math.min(buffer.length, fileSizeBytes - written);
        out.write(buffer, 0, toWrite);
        written += toWrite;
      }
    }

    long finalSize = Files.size(largeFile);
    assertEquals(fileSizeBytes, finalSize, "Generated file must be exactly the specified size");
  }
}