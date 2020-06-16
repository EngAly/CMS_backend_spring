package com.fci.utils;

import java.io.File;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.fci.interfaces.Diagnostics;
import com.fci.interfaces.Peculiar;
import com.fci.models.Images;
import com.fci.models.Patient;

public class Utils {

	private static ArrayList<String> names;
	private static ArrayList<Images> patientImages;

	private static Set temp;
	private static String message = "";

	public static ArrayList<String> getNames(Set<?> infos) {
		names = new ArrayList<String>();
		// from patient instance get all symptoms (complaint, diseases, medical,exams,
		// ...) names only
		infos.forEach(item -> names.add(((Peculiar) item).getName()));
		return names;
	}

	public static <T> Set<T> getDiagnosticRecords(Patient patient, Diagnostics<T> diagnostics) {
		temp = new HashSet<T>();
		temp = diagnostics.getRecordByName(patient);
		return temp;
	}

	/**
	 * ------------- Save Images To Patients folder with id folder
	 * name----------<br>
	 * 
	 * @param files :images that migrated from front end to back end
	 * @param id    :patient id that client want to add it to database to use it as
	 *              part of patient folder name to save patient images
	 * @return
	 */
	public static String savePatientImages(MultipartFile[] files, Long id) {
		File directory = new File("Patients/" + id);
		Optional.of(!directory.exists()).ifPresent((d) -> directory.mkdirs());
		for (MultipartFile file : files) {
			Path path = Paths.get(directory + "/" + file.getOriginalFilename());
			try {
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
				message = "images saved successfully";
			} catch (Exception e) {
				message = e.getMessage();
			}
		}
		return message;
	}

	/**
	 * ------------- Retrieve Patient Images ---------------<br
	 * 
	 * @param patientId : id for defined patient to get its images
	 * @return
	 */
	public static ArrayList<Images> retrievePatientImages(Long id) {
//      array that contain all patient images
		patientImages = new ArrayList<>();
//      path to patient image inner server
		String patientImagesPath = "Patients/" + id;
//      convert all image paths to files
		File imagePaths = new File(patientImagesPath);
		if (imagePaths.exists()) {
			for (File image : imagePaths.listFiles()) {
				try {
//					create instance of image and add it to list so after loop patientImages instance will has all patient images
					patientImages.add(new Images(image.getName(), "image/*",
							Files.readAllBytes(Paths.get(patientImagesPath + "/" + image.getName()))));
				} catch (Exception e) {
					e.getMessage();
				}
			}
		}
		return patientImages;
	}

	public static void deletePatientFolder(Long id) {
		String patientImagesPath = "Patients/" + id;
		try {
			Path patientImages = Paths.get(patientImagesPath);
			Files.walk(patientImages, FileVisitOption.FOLLOW_LINKS).sorted(Comparator.reverseOrder()).map(Path::toFile)
					.forEach(File::delete);
		} catch (Exception e) {
		}

	}
}
