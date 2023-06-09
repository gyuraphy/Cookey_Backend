package com.k404.Cookey.domain.recipe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import com.k404.Cookey.domain.recipe.entity.Recipe;
import com.k404.Cookey.domain.user.entity.User;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class ParentRecipeDto {
    private Long id;
    private String title;
    private String description;
    private String thumbnail;

    private List<IngredientDto> mainIngredients;
    private List<IngredientDto> subIngredients;
    private List<ThemeDto> themes;
    private List<StepDto> steps;

    private int time;

    private UserDto writer;

    public ParentRecipeDto(Recipe recipe){
        id = recipe.getId();
        title = recipe.getTitle();
        description = recipe.getDescription();
        thumbnail = recipe.getThumbnail();

        mainIngredients = recipe.getMainIngredients().stream()
                .map(i->new IngredientDto(i.getName()))
                .collect(Collectors.toList());
        subIngredients = recipe.getSubIngredients().stream()
                .map(i->new IngredientDto(i.getName()))
                .collect(Collectors.toList());

        themes = recipe.getThemes().stream()
                .map(t->new ThemeDto(t.getId(),t.getName()))
                .collect(Collectors.toList());

        steps = recipe.getSteps().stream()
                .map(s->new StepDto(s.getId(), s.getDescription(),s.getImageUrl(),s.getSequence()))
                .collect(Collectors.toList());

        time = recipe.getTime();

        User user = recipe.getUser();

        writer = new UserDto(user.getId(),user.getName(), user.getEmail(), user.getImageUrl());

    }
}
