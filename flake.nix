{
  outputs = inputs@{ ... }:
    let
      systems = [
        "x86_64-linux"
        "aarch64-linux"
      ];

      forAllSystems = function:
        inputs.nixpkgs.lib.genAttrs systems
          (system:
            function (import inputs.nixpkgs { inherit system; })
          );
    in
    {
      devShells = forAllSystems (pkgs:
        {
          default = pkgs.mkShell {
            packages = with pkgs; [
              maven
              jdk17
            ];
          };
        });
    };

  inputs = {
    nixpkgs.url = "nixpkgs/nixos-unstable";
  };
}
