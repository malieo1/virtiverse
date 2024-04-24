/*
package com.example.virtiverse.config;


import com.google.auth.oauth2.GoogleCredentials;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import java.io.FileInputStream;
import java.io.IOException;
*/
/*
@Configuration
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CloudStorageConfig {

    @Value("${google.cloud.storage.credentials.location}")
    private String credentialsLocation;

    @Bean
    public Storage storage() throws IOException {
        // Créer un objet GoogleCredentials à partir du fichier JSON
        GoogleCredentials credentials = GoogleCredentials.fromStream(
                new FileInputStream(credentialsLocation));

        // Créer une instance de stockage Google Cloud Storage en utilisant les clés d'authentification
        return StorageOptions.newBuilder().setCredentials(credentials).build().getService();
    }
}
*/

