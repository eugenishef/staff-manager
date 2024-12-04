package ru.safeline.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "Сущность сотрудника")
public class EmployeeDto {
    @Null(message = "Поле 'id' должно быть пустым при создании сотрудника")
    @Schema(description = "Идентификатор сотрудника", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    Long id;

    @NotBlank(message = "Имя сотрудника не может быть пустым")
    @Size(max = 50, message = "Имя не должно быть длиннее 50 символов")
    @Pattern(regexp = "^[^\\s]+$", message = "Имя не может содержать пробелов")
    @Schema(description = "Имя сотрудника", example = "Иван")
    String name;

    @NotBlank(message = "Роль сотрудника не может быть пустой")
    @Schema(description = "Должность сотрудника", example = "Developer")
    String role;
}
