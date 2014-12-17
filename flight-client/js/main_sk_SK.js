/**
 * Created by PC on 27. 11. 2014.
 */
$(document).ready(function () {
    $('.data-table').DataTable(
        {
            searching: false,
            lengthChange: false,
            language: {
                "emptyTable":     "Nie sú k dispozícii žiadne dáta",
                "info":           "Záznamy _START_ až _END_ z celkom _TOTAL_",
                "infoEmpty":      "Záznamy 0 až 0 z celkom 0 ",
                "infoFiltered":   "(vyfiltrované spomedzi _MAX_ záznamov)",
                "infoPostFix":    "",
                "infoThousands":  ",",
                "lengthMenu":     "Zobraz _MENU_ záznamov",
                "loadingRecords": "Načítavam...",
                "processing":     "pracúvam...",
                "search":         "Hľadať:",
                "zeroRecords":    "Nenašli sa žiadne vyhovujúce záznamy",
                "paginate": {
                    "first":    "Prvá",
                    "last":     "Posledná",
                    "next":     "Nasledujúca",
                    "previous": "Predchádzajúca"
                },
                "aria": {
                    "sortAscending":  ": aktivujte na zoradenie stĺpca vzostupne",
                    "sortDescending": ": aktivujte na zoradenie stĺpca zostupne"
                }
            }
        }
    );

});
