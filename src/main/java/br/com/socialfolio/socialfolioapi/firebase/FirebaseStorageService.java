package br.com.socialfolio.socialfolioapi.firebase;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FirebaseStorageService {
    private Bucket bucket;

    public String uploadFiletoFirebase(MultipartFile file, String fileName, String basePath) throws IOException {
        Bucket bucket = getBucket();
        if (bucket != null) {
            String filePath = basePath + fileName; //Caminho completo do arquivo no bucket
            Blob blob = bucket.create(filePath, file.getBytes());
            var fbaseFileLocation = blob.getName();
            return fbaseFileLocation;
        }
        throw new IllegalStateException("Firebase não está inicializado corretamente.");
    }

    public byte[] getFileFromFirebase(String filePath) throws IOException {
        Bucket bucket = getBucket();
        if (bucket != null) {
            Blob blob = bucket.get(filePath);
            if (blob != null) {
                byte[] content = blob.getContent();
                return content;
            } else {
                throw new FileNotFoundException("Arquivo não encontrado no Firebase: " + filePath);
            }
        }
        throw new IllegalStateException("Firebase não está inicializado corretamente.");
    }


    public Bucket getBucket() {
        if (bucket == null) {
            bucket = StorageClient.getInstance().bucket();
        }
        return bucket;
    }
}