package com.huiyee.esite.handler;

import java.io.Serializable;

public abstract class Handler implements Serializable
{
	public abstract void work(Object o);
}
