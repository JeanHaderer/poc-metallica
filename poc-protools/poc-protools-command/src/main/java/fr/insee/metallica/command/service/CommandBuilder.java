package fr.insee.metallica.command.service;

import java.time.LocalDateTime;

import fr.insee.metallica.command.domain.Command;
import fr.insee.metallica.command.domain.Command.Status;

public class CommandBuilder extends CommandBuilderBase<CommandBuilder> {
	private AsyncResultFetcherBuilder asyncResult;
	protected final CommandProcessorService commandProcessorService;

	public CommandBuilder(CommandService commandService, CommandProcessorService commandProcessorService, String type) {
		super(commandService, type);
		this.commandProcessorService = commandProcessorService;
	}
	
	@Override
	protected CommandBuilder getThis() {
		return this;
	}
	
	public AsyncResultFetcherBuilder asyncResult(String type) {
		return this.asyncResult = new AsyncResultFetcherBuilder(commandService, this, type);
	}
	
	public Command saveAndSend() {
		return saveAndSend(0, null);
	}

	public Command saveAndSend(int limit, String limitKey) {
		return save(limit, limitKey, true);
	}
	
	public Command save(int limit, String limitKey, boolean shouldSend) {
		command.setStatus(Status.Pending);
		if (limit > 0 && limitKey != null) {
			command.setConcurrencyLimit(limit);
			command.setLimitKey(limitKey);
		}
		if (command.getNextScheduledTime() == null)
			command.setNextScheduledTime(LocalDateTime.now());
		
		return this.commandService.executeInTransaction(command, asyncResult == null ? null : asyncResult.build());
	}

	public Command saveNoSend() {
		command.setStatus(Status.Pending);
		if (command.getNextScheduledTime() == null)
			command.setNextScheduledTime(LocalDateTime.now());
		return this.commandService.transactionTemplate.execute((status) -> {
			command = this.commandService.commandRepository.save(command);
			return command;
		});
	}
}