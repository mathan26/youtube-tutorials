package com.tech.mathan.spring_log_monitoring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedDeque;

@RestController
@RequestMapping("/api/logs")
public class LogController {

	private static final Logger logger = LoggerFactory.getLogger(LogController.class);
	private static final int MAX_LOG_ENTRIES = 1000;
	private static final ConcurrentLinkedDeque<LogEntry> logHistory = new ConcurrentLinkedDeque<>();

	@PostMapping
	public ResponseEntity<Map<String, String>> logMessage(@RequestBody LogRequest request) {
		if (request == null || request.message() == null || request.message().isBlank()) {
			return ResponseEntity.badRequest().body(Map.of("error", "Message cannot be empty"));
		}

		String level = request.level() != null ? request.level().toUpperCase() : "INFO";
		String message = request.message();

		LogEntry entry = new LogEntry(level, message);
		addToHistory(entry);

		switch (level) {
			case "WARN" -> logger.warn(message);
			case "ERROR" -> logger.error(message);
			case "DEBUG" -> logger.debug(message);
			case "TRACE" -> logger.trace(message);
			default -> logger.info(message);
		}

		return ResponseEntity.ok(Map.of(
				"status", "success",
				"level", level,
				"message", message,
				"timestamp", entry.timestamp()
		));
	}

	@PostMapping("/info")
	public ResponseEntity<Map<String, String>> logInfo(@RequestBody Map<String, String> body) {
		return logMessage(new LogRequest("INFO", body.get("message")));
	}

	@PostMapping("/warn")
	public ResponseEntity<Map<String, String>> logWarn(@RequestBody Map<String, String> body) {
		return logMessage(new LogRequest("WARN", body.get("message")));
	}

	@PostMapping("/error")
	public ResponseEntity<Map<String, String>> logError(@RequestBody Map<String, String> body) {
		return logMessage(new LogRequest("ERROR", body.get("message")));
	}

	@GetMapping
	public ResponseEntity<List<LogEntry>> getRecentLogs(
			@RequestParam(defaultValue = "50") int limit,
			@RequestParam(required = false) String level) {

		int maxLimit = Math.min(Math.max(limit, 1), 500);
		List<LogEntry> entries = new ArrayList<>(logHistory);
		Collections.reverse(entries);

		if (level != null && !level.isBlank()) {
			String filterLevel = level.toUpperCase();
			entries = entries.stream()
					.filter(e -> e.level().equals(filterLevel))
					.limit(maxLimit)
					.toList();
		} else {
			entries = entries.stream().limit(maxLimit).toList();
		}

		return ResponseEntity.ok(entries);
	}

	@GetMapping("/count")
	public ResponseEntity<Map<String, Long>> getLogCounts() {
		List<LogEntry> entries = new ArrayList<>(logHistory);
		long infoCount = entries.stream().filter(e -> e.level().equals("INFO")).count();
		long warnCount = entries.stream().filter(e -> e.level().equals("WARN")).count();
		long errorCount = entries.stream().filter(e -> e.level().equals("ERROR")).count();
		long debugCount = entries.stream().filter(e -> e.level().equals("DEBUG")).count();

		return ResponseEntity.ok(Map.of(
				"INFO", infoCount,
				"WARN", warnCount,
				"ERROR", errorCount,
				"DEBUG", debugCount,
				"TOTAL", (long) entries.size()
		));
	}

	@GetMapping("/errors")
	public ResponseEntity<List<LogEntry>> getErrorLogs(@RequestParam(defaultValue = "20") int limit) {
		int maxLimit = Math.min(Math.max(limit, 1), 500);
		List<LogEntry> entries = new ArrayList<>(logHistory);
		Collections.reverse(entries);

		List<LogEntry> errors = entries.stream()
				.filter(e -> e.level().equals("ERROR"))
				.limit(maxLimit)
				.toList();

		return ResponseEntity.ok(errors);
	}

	private void addToHistory(LogEntry entry) {
		logHistory.add(entry);
		while (logHistory.size() > MAX_LOG_ENTRIES) {
			logHistory.pollFirst();
		}
	}

	public record LogRequest(String level, String message) {}

	public record LogEntry(String level, String message, String timestamp) {
		public LogEntry(String level, String message) {
			this(level, message, Instant.now().toString());
		}
	}
}
