package io.dustin.finstage.financial.docs;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "SymbolListResponseExample", example = """
{
  "status": 200,
  "message": "Success",
  "data": {
    "total": 3814,
    "page": 1,
    "size": 2,
    "totalPages": 1908,
    "hasNext": true,
    "hasPrev": false,
    "items": [
      {
        "symbol": "AACB",
        "name": "Artius II Acquisition Inc. Class A Ordinary Shares",
        "country": "United States"
      },
      {
        "symbol": "AACBR",
        "name": "Artius II Acquisition Inc. Rights",
        "country": "United States"
      }
    ]
  },
  "errors": null
}
""")
public class GetSymbolExample {
}

