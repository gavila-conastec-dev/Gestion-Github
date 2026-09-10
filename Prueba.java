package com.conastec.sfe;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import com.conastec.framework.util.XmlConverterUtil;
import com.conastec.ubl.oasis.names.specification.ubl.schema.xsd.invoice_2.InvoiceType;

public class Prueba {

	public static void main(String[] args) {
		try {
			String ruta = "/home/cllontop/Documentos/Ebis/Tareas/2025/Setiembre/10-09-2025/10435151898-01-E001-1340/20109922731-01-F001-00018454.xml";
			removeBomFromFile(ruta);
			InvoiceType invoice = (InvoiceType) XmlConverterUtil.xmlFileToObject(ruta, InvoiceType.class);
			System.out.println("Archivo correcto : " + invoice.getID().getValue());
		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		}
	}

	public static final byte[] UTF8_BOM = new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF };

	public static void removeBomFromFile(String filePath) throws IOException {
		Path path = Paths.get(filePath);
		byte[] fileBytes = Files.readAllBytes(path);

		if (fileBytes.length >= 3 && Arrays.equals(Arrays.copyOfRange(fileBytes, 0, 3), UTF8_BOM)) {
			// BOM found, create a new byte array without the BOM
			byte[] contentWithoutBom = Arrays.copyOfRange(fileBytes, 3, fileBytes.length);
			Files.write(path, contentWithoutBom); // Overwrite the original file
			System.out.println("BOM removed from: " + filePath);
		} else {
			System.out.println("No BOM found in: " + filePath);
		}
	}

}
