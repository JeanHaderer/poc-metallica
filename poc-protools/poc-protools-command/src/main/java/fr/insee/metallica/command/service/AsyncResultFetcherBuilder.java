package fr.insee.metallica.command.service;

import fr.insee.metallica.command.domain.Command;

public class AsyncResultFetcherBuilder extends CommandBuilderBase<AsyncResultFetcherBuilder> {
	private final CommandBuilder parent;
	
	@Override
	protected AsyncResultFetcherBuilder getThis() {
		return this;
	}
	
	public AsyncResultFetcherBuilder(CommandService commandService, CommandBuilder parent, String type) {
		super(commandService, type);
		this.parent = parent;
	}
	
	public CommandBuilder and() {
		return parent;
	}
	
	public Command saveAndSend() {
		return parent.saveAndSend();
	}

	public Command saveAndSendWithLimit(int limit, String limitKey) {
		return parent.saveAndSend(limit, limitKey);
	}

	public Command saveNoSend() {
		return parent.saveNoSend();
	}
}