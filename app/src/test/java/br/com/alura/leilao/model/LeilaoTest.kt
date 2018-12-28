package br.com.alura.leilao.model

import org.junit.Test

import org.junit.Assert.*

class LeilaoTest {
    private val leilao = Leilao("Console");
    private val usuarioTeste1 = Usuario ("teste1")
    private val DELTA: Double = 0.0001

    @Test
    fun When_RecebeDescricao_Expect_Descricao() {
        val descricaoDevolvida = leilao.descricao
        assertEquals("Console", descricaoDevolvida)
    }

    /*Testes getMaiorLance()*/
    @Test
    fun When_RecebeUnicoLance_Expect_MaiorLance(){
        leilao.propoe(Lance(usuarioTeste1,200.0))
        val maiorLance = leilao.maiorLance
        assertEquals(200.0, maiorLance, DELTA)
    }

    @Test
    fun When_RecebeLancesOrdemCrescente_Expect_MaiorLance(){
        val usuarioTeste2 = Usuario ("teste2")
        leilao.propoe(Lance(usuarioTeste1,200.0))
        leilao.propoe(Lance(usuarioTeste2,300.0))
        val maiorLance = leilao.maiorLance
        assertEquals(300.0, maiorLance, DELTA)
    }

    @Test
    fun When_RecebeLancesOrdemDecrescente_Expect_MaiorLance(){
        val usuarioTeste2 = Usuario ("teste2")
        leilao.propoe(Lance(usuarioTeste1,300.0))
        leilao.propoe(Lance(usuarioTeste2,200.0))
        val maiorLance = leilao.maiorLance
        assertEquals(300.0, maiorLance, DELTA)
    }

    /*Testes getMenorLance()*/
    @Test
    fun When_RecebeUnicoLance_Expect_MenorLance(){
        leilao.propoe(Lance(usuarioTeste1,200.0))
        val menorLance = leilao.menorLance
        assertEquals(200.0, menorLance, DELTA)
    }

    @Test
    fun  When_RecebeLancesOrdemCrescente_Expect_MenorLance(){
        val usuarioTeste2 = Usuario ("teste2")
        leilao.propoe(Lance(usuarioTeste1,200.0))
        leilao.propoe(Lance(usuarioTeste2,300.0))
        val menorLance = leilao.menorLance
        assertEquals(200.0, menorLance, DELTA)
    }

    @Test
    fun When_RecebeLancesOrdemDecrescente_Expect_MenorLance(){
        val usuarioTeste2 = Usuario ("teste2")
        leilao.propoe(Lance(usuarioTeste1,300.0))
        leilao.propoe(Lance(usuarioTeste2,200.0))
        val menorLance = leilao.menorLance
        assertEquals(200.0, menorLance, DELTA)
    }


    @Test
    fun When_RecebeTresLances_Expect_TresMaioresLances(){
        leilao.propoe(Lance(usuarioTeste1, 200.0))
        leilao.propoe(Lance(Usuario("Teste2"), 300.0))
        leilao.propoe(Lance(usuarioTeste1, 400.0))

        val maioresLances : List<Lance> = leilao.tresMaioresLances()

        assertEquals(3, maioresLances.size)
        assertEquals(400.0, maioresLances[0].valor, DELTA)
        assertEquals(300.0, maioresLances[1].valor, DELTA)
        assertEquals(200.0, maioresLances[2].valor, DELTA)
    }

    @Test
    fun When_RecebeNenhumLance_Expect_TresMaioresLances(){
        val tresMaioresLances = leilao.tresMaioresLances()
        assertEquals(0, tresMaioresLances.size)
    }

    @Test
    fun When_RecebeUmLance_Expect_TresMaioresLances(){
        leilao.propoe(Lance(usuarioTeste1, 200.0))

        val tresMaioresLances = leilao.tresMaioresLances()
        assertEquals(1,tresMaioresLances.size)
        assertEquals(200.0, tresMaioresLances[0].valor, DELTA)

    }

    @Test
    fun When_RecebeDoisLances_Expect_TresMaioresLances(){
        leilao.propoe(Lance(usuarioTeste1, 200.0))
        leilao.propoe(Lance(Usuario("Teste2"), 100.0))

        val tresMaioresLances = leilao.tresMaioresLances()

        assertEquals(2, tresMaioresLances.size)
        assertEquals(200.0, tresMaioresLances[0].valor, DELTA)
        assertEquals(100.0, tresMaioresLances[1].valor, DELTA)
    }


    @Test
    fun When_RecebeMaisDeTresLances_Expect_TresMaioresLances(){
        val usuarioTeste2 = Usuario("Teste2")
        leilao.propoe(Lance(usuarioTeste1, 100.0))
        leilao.propoe(Lance(usuarioTeste2, 300.0))
        leilao.propoe(Lance(usuarioTeste1, 500.0))
        leilao.propoe(Lance(usuarioTeste2, 200.0))

        val tresMaioresLances = leilao.tresMaioresLances()

        assertEquals(3, tresMaioresLances.size)
        assertEquals(500.0, tresMaioresLances[0].valor, DELTA)
        assertEquals(300.0, tresMaioresLances[1].valor, DELTA)
        assertEquals(200.0, tresMaioresLances[2].valor, DELTA)

        leilao.propoe(Lance(usuarioTeste1, 700.0))

        val tresMaioresLancesNovo = leilao.tresMaioresLances()

        assertEquals(3, tresMaioresLancesNovo.size)
        assertEquals(700.0, tresMaioresLancesNovo[0].valor, DELTA)
        assertEquals(500.0, tresMaioresLancesNovo[1].valor, DELTA)
        assertEquals(300.0, tresMaioresLancesNovo[2].valor, DELTA)
    }



}