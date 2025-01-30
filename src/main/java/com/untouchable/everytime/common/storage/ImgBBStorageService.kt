package com.untouchable.everytime.common.storage

import io.github.oshai.kotlinlogging.KotlinLogging
import java.util.Base64
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestClient
import org.springframework.web.multipart.MultipartFile

@Component
class ImgBBStorageService(
    @Value("\${storage.imgbb.api-key}") private val apiKey: String,
) : StorageService {

    private val restClient: RestClient = RestClient.create()

    private fun uploadImage(image: MultipartFile): String {
        // Multipart/form-data 형식으로 이미지를 Base64로 인코딩하여 전송
        val parts: MultiValueMap<String, Any> = LinkedMultiValueMap()
        parts.add("image", Base64.getEncoder().encodeToString(image.bytes))


        val response: Map<*, *>? = restClient.post()
            .uri("$IMGBB_API_URL?key=$apiKey")
            .contentType(MediaType.MULTIPART_FORM_DATA)
            .body(parts)
            .retrieve()
            .body(Map::class.java)

        // 업로드 결과에서 이미지 URL 추출
        val data = response?.get("data") as Map<*, *>?
        return data?.get("url").toString()
    }

    override fun save(image: MultipartFile): String {
        return uploadImage(image)
    }

    override fun saveAll(images: List<MultipartFile>): List<String> {
        return images.map { uploadImage(it) }
    }


    init {
        log.info { "ImgBBStorageService initialized" }
    }

    companion object {
        val log = KotlinLogging.logger {}
        const val IMGBB_API_URL = "https://api.imgbb.com/1/upload"
    }
}
