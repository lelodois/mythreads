package br.com.lelo.threads;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Assert;
import org.junit.Test;

import br.com.lelo.threads.map.InclusaoExclusaoMap;

public class InclusaoExclusaoMapTest {

	@Test
	public void test1000() {
		this.start(1000, getSyncronizedMap());
	}

	@Test
	public void test10000() {
		this.start(10000, getSyncronizedMap());
	}

	@Test
	public void test100000() {
		this.start(100000, getSyncronizedMap());
	}

	@Test(expected = Exception.class)
	public void test100000OnError() throws Exception {
		new InclusaoExclusaoMap().go(100000, new HashMap<Integer, String>());
	}

	private Map<Integer, String> getSyncronizedMap() {
		return new ConcurrentHashMap<Integer, String>();
	}

	private void start(int size, Map<Integer, String> map) {
		try {
			new InclusaoExclusaoMap().go(size, map);
			System.out.println(map.size());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Erro ao remover algum item");
		}
	}
}
