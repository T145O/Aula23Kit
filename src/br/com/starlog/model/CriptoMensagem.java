package br.com.starlog.model;

/**
 * CAMADA MODEL (Modelo de Domínio)
 * Representa uma mensagem criptográfica a ser transmitida.
 * 
 * ATENÇÃO ESTUDANTE:
 * Implemente esta classe a partir do código extraído da TelaCaoticaLegada
 * para satisfazer os testes TDD de 01 a 06!
 * 
 * REGRA SAGRADA DE ARQUITETURA:
 * O Model NUNCA deve importar componentes visuais (zero imports de
 * javax.swing.*)!
 */
public class CriptoMensagem {
    private final String remetente;
    private final String mensagem;
    private final int chaveDeslocamento;

    public CriptoMensagem(String remetente, String mensagem, int chaveDeslocamento) {
        // TODO: Etapa 2 - Implementar barreira Fail-Fast
        // FEITO!!
        // 1. Validar se remetente é nulo ou vazio -> lançar
        // IllegalArgumentException("Remetente não pode ser nulo ou vazio.");
        // FEITO!!
        if (remetente == null) {
            throw new IllegalArgumentException("remetente não pode ser nulo ou vazio");
        }
        // 2. Validar se mensagem é nula ou vazia -> lançar
        // IllegalArgumentException("Mensagem não pode ser nula ou vazia.");
        // FEITO!!
        if (mensagem == null) {
            throw new IllegalArgumentException("Mensagem não pode ser nula ou vazia.");
        }
        // 3. Validar se chaveDeslocamento está fora de 1..25 -> lançar
        // IllegalArgumentException("Chave deve estar entre 1 e 25.");
        // FEITO!!
        if (chaveDeslocamento < 1 || chaveDeslocamento > 25) {
            throw new IllegalArgumentException("Chave deve estar entre 1 e 25");
        }
        // 4. Normalizar remetente e mensagem (.trim().toUpperCase())
        // FEITO!!
        this.remetente = remetente.trim().toUpperCase();
        this.mensagem = mensagem.trim().toUpperCase();
        this.chaveDeslocamento = chaveDeslocamento;
    }

    public String getRemetente() {
        return remetente;
    }

    public String getMensagem() {
        return mensagem;
    }

    public int getChaveDeslocamento() {
        return chaveDeslocamento;
    }

    /**
     * Aplica o algoritmo da Cifra de César com deslocamento circular ('A'..'Z').
     * Preserva espaços e pontuações sem alteração.
     */
    public String cifrar(int deslocamento, String msg) {
        // TODO: Etapa 2 - Extrair algoritmo da Cifra de César da TelaCaoticaLegada
        // Dica: Para cada caractere entre 'A' e 'Z', rotacionar circularmente:
        // char cifrado = (char) ('A' + (c - 'A' + this.chaveDeslocamento) % 26);
        //FEITO

        // String Builder serve para permitir a alteração de uma string em JAVA;
        StringBuilder cifrado = new StringBuilder();
        String textoLimpo = msg.trim().toUpperCase();
        //Laço percorre todas as letras do texto e as altera uma por uma;
        for (int i = 0; i < textoLimpo.length(); i++) {
            char c = textoLimpo.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                char letraCifrada = (char) ('A' + (c - 'A' + deslocamento) % 26);
                cifrado.append(letraCifrada);
            } else {
                cifrado.append(c); // Mantém espaços e pontuações
            }
        }
        return cifrado.toString();
    }

    /**
     * Calcula o checksum somando o valor ordinal ASCII de cada caractere da
     * mensagem em maiúsculo.
     */
    public int calcularChecksum() {
        //TODO: Etapa 2 - Extrair cálculo de Checksum da TelaCaoticaLegada
        //Somar o código ASCII de todos os caracteres da mensagem
        int checksum = 0;
                for (char c : mensagem.toCharArray()) {
                    checksum += (int) c;
                }
                String pacoteFinal = String.format("[%s] #%d -> %s", remetente.trim()
                .toUpperCase(), checksum, mensagem.toString());
        return checksum;
    }
}
