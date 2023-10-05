package med.voll.api.endereco;

public record DadosEnderenco(String logradouro, String bairro, String cep, String uf, String complemento, String numero) {
    public String cidade() {
        return null;
    }
}
