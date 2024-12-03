package ru.safeline.api.dto;

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
public class EmployeeDto {
    @Null(message = "Поле 'id' должно быть пустым при создании сотрудника")
    Long id;

    @NotBlank(message = "Имя сотрудника не может быть пустым")
    @Size(max = 50, message = "Имя не должно быть длиннее 50 символов")
    @Pattern(regexp = "^[^\\s]+$", message = "Имя не может содержать пробелов")
    String name;

    @NotBlank(message = "Роль сотрудника не может быть пустой")
    String role;
}
