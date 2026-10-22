package com.moviesRecommendation.service;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class MovieService {
    private final EmbeddingModel embeddingModel;


    public MovieService(EmbeddingModel embeddingModel) {
        this.embeddingModel = embeddingModel;
    }
    public void search(String query){
       float[] embeddings = embeddingModel.embed(query);
        System.out.println(embeddings.length);
        System.out.println(Arrays.toString(embeddings));
    }
}
