package com.practice.shop.exception;

import com.practice.shop.exception.item.ItemNotFoundException;
import com.practice.shop.exception.item.ItemPriceInvalidException;
import com.practice.shop.exception.item.ItemTitleBlankException;
import com.practice.shop.exception.item.PageNotFoundException;
import com.practice.shop.exception.member.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> argMismatch() {
        return ResponseEntity.status(500).body("잘못된 요청입니다.");
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<String> authorizationDenied() {
        return ResponseEntity.status(403).body("권한이 없습니다.");
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<String> noResourceFound() {
        return ResponseEntity.status(404).body("해당 페이지는 존재하지 않습니다.");
    }

    /* ---------------- item exception ---------------- */

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<String> itemNotFound(ItemNotFoundException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(ItemTitleBlankException.class)
    public ResponseEntity<String> blankTitle(ItemTitleBlankException e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }

    @ExceptionHandler(ItemPriceInvalidException.class)
    public ResponseEntity<String> invalidPrice(ItemPriceInvalidException e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }

    @ExceptionHandler(PageNotFoundException.class)
    public ResponseEntity<String> pageNotFound(PageNotFoundException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }

    /* ---------------- member exception ---------------- */

    @ExceptionHandler(IdDuplicateException.class)
    public ResponseEntity<String> duplicateId(IdDuplicateException e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }

    @ExceptionHandler(IdBlankException.class)
    public ResponseEntity<String> blankId(IdBlankException e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }

    @ExceptionHandler(PasswordBlankException.class)
    public ResponseEntity<String> blankPassword(PasswordBlankException e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }

    @ExceptionHandler(DisplayNameBlankException.class)
    public ResponseEntity<String> blankDisplayName(DisplayNameBlankException e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }

    @ExceptionHandler(UserNotAuthenticatedException.class)
    public ResponseEntity<String> notAuthenticated(UserNotAuthenticatedException e) {
        return ResponseEntity.status(401).body(e.getMessage());
    }
}
