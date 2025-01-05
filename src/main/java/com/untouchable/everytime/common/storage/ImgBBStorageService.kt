package com.untouchable.everytime.common.storage

import io.github.oshai.kotlinlogging.KotlinLogging
import java.util.Base64
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestTemplate
import org.springframework.web.multipart.MultipartFile

@Component
class ImgBBStorageService(
    @Value("\${storage.imgbb.api-key}") private val apiKey: String,
) : StorageService {

    private val restTemplate: RestTemplate = RestTemplate()

    private fun uploadImage(image: MultipartFile): String {
        // Multipart/form-data 형식으로 이미지를 Base64로 인코딩하여 전송
        val requestEntity = HttpEntity(
            LinkedMultiValueMap<String, Any>().apply {
                add("image", Base64.getEncoder().encodeToString(image.bytes))
            }, HttpHeaders().apply {
                contentType = MediaType.MULTIPART_FORM_DATA
            }
        )

        // API 호출 및 응답 처리
        val response = restTemplate.exchange(
            "$IMGBB_API_URL?key=$apiKey",
            HttpMethod.POST,
            requestEntity,
            Map::class.java
        )

        // 업로드 결과에서 이미지 URL 추출
        val data = response.body?.get("data") as Map<*, *>?
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
